package se.kth.iv1350.daniel.controller;

import se.kth.iv1350.daniel.controller.exceptions.ConnectionFailed;
import se.kth.iv1350.daniel.integration.ReceiptPrinter;
import se.kth.iv1350.daniel.integration.Register;
import se.kth.iv1350.daniel.integration.accounting_system.AccountingSystem;
import se.kth.iv1350.daniel.integration.discount_db.DiscountDB;
import se.kth.iv1350.daniel.integration.inventory_db.InventoryDAO;
import se.kth.iv1350.daniel.integration.inventory_db.inventory_exc.DatabaseConnectionFailed;
import se.kth.iv1350.daniel.integration.inventory_db.inventory_exc.ItemDoesNotExist;
import se.kth.iv1350.daniel.model.*;
import se.kth.iv1350.daniel.model.dto.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Controller
{
    private Sale myCurrentSale;
    private final List <SaleObserver> saleObservers;
    private final LogHandler logger;

    public Controller( ) throws IOException
    {
        saleObservers = new ArrayList<>();
        this.logger = new LogHandler();
    }

    public void addSaleObserver(SaleObserver observer)
    {
        saleObservers.add(observer);
    }
    /**
     * Starts a new sale by initializing a new Sale instance.
     */
    public void startNewSale()
    {
        this.myCurrentSale = new Sale();
        this.myCurrentSale.addObservers(saleObservers);


    }

    /**
     * Ends the current sale and logs it.
     * Clears the current sale information.
     */
    public void endSale()
    {
        SaleLog.getInstance().addSale(this.myCurrentSale.getSaleDTO());
        this.myCurrentSale = null;
    }

    /**
     * Adds an item to the current sale or increases its quantity if already added.
     *
     * @param itemId   The ID of the item to add.
     * @param quantity The quantity of the item to add.
     * @return LastSaleUpdateDTO containing information about the updated sale.
     */
    public LastSaleUpdateDTO addItem(int itemId, int quantity) throws ItemDoesNotExist, ConnectionFailed
    {
        try
        {

            if (myCurrentSale.contains(itemId))
            {
                return myCurrentSale.increaseItemQuantity(itemId, quantity);
            }
            else
            {
                Item itemToAdd = new Item(InventoryDAO.getInstance().fetchItem(itemId), quantity);
                return myCurrentSale.addItem(itemToAdd);
            }
        }
        catch (DatabaseConnectionFailed dbExc)
        {
            logger.logException(dbExc);
            throw new ConnectionFailed("Ops! There has been an issue to connect to database, pls try again!");
        }
    }

    /**
     * Applies discounts on the current sale based on items and total price.
     *
     * @return List of AppliedDiscountDTOs containing information about applied discounts.
     */
    public List<AppliedDiscountDTO> applyDiscountsOnSale()
    {
        List<AppliedDiscountDTO> appliedDiscounts = new ArrayList<>();
        List<ItemDTO> shopList = myCurrentSale.getShopList();
        DiscountDTO itemDisInfo = DiscountDB.getInstance().findDiscountByShopList(shopList);
        appliedDiscounts.add(myCurrentSale.applyDiscount(itemDisInfo));
        double totalPrice = myCurrentSale.getTotalPrice();
        DiscountDTO totalPriceInfo = DiscountDB.getInstance().findDiscountByTotalSum(totalPrice);
        appliedDiscounts.add(myCurrentSale.applyDiscount(totalPriceInfo));
        return appliedDiscounts;
    }

    /**
     * Applies a discount based on the provided customer ID to the current sale.
     *
     * @param customerId The ID of the customer for which the discount is to be applied.
     * @return The AppliedDiscountDTO containing information about the applied discount.
     */
    public AppliedDiscountDTO applyDiscountByCustomerId(int customerId)
    {
        DiscountDTO disInfo = DiscountDB.getInstance().findDiscountByCustomerId(customerId);
        return myCurrentSale.applyDiscount(disInfo);
    }

    /**
     * Processes a payment for the current sale.
     *
     * @param amount The amount paid by the customer.
     * @return The change returned to the customer.
     */
    public double pay(double amount)
    {
        ReceiptDTO receipt = myCurrentSale.pay(new Payment(amount));
        InventoryDAO.getInstance().updateInventory(receipt.getShopList());
        AccountingSystem.getInstance().updateAccountingSystem(receipt.saleInfo());
        Register.getInstance().increaseAmount(receipt.amountPaid());
        Register.getInstance().decreaseAmount(receipt.changeAmount());
        ReceiptPrinter.getInstance().printReceipt(receipt);
        return receipt.changeAmount();
    }
}
