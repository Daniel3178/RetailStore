package se.kth.iv1350.daniel.controller;

import se.kth.iv1350.daniel.integration.ExternalSysCreator;
import se.kth.iv1350.daniel.integration.ReceiptPrinter;
import se.kth.iv1350.daniel.integration.Register;
import se.kth.iv1350.daniel.integration.accounting_system.AccountingSystem;
import se.kth.iv1350.daniel.integration.discount_db.DiscountDB;
import se.kth.iv1350.daniel.integration.inventory_db.InventoryDAO;
import se.kth.iv1350.daniel.model.*;
import se.kth.iv1350.daniel.model.dto.*;

import java.util.ArrayList;
import java.util.List;


public class Controller
{
    private Sale myCurrentSale;
    private final AccountingSystem myAccountingSys;
    private final DiscountDB myDiscountDb;
    private final InventoryDAO myInventoryDAO;
    private final Register myRegister;
    private final ReceiptPrinter myReceiptPrinter;

    /**
     * Initializes a new Controller instance with dependencies from ExternalSysCreator.
     *
     * @param externalSysCreator An instance of ExternalSysCreator providing external systems.
     */
    public Controller(ExternalSysCreator externalSysCreator)
    {
        this.myAccountingSys = externalSysCreator.getAccountingSystem();
        this.myDiscountDb = externalSysCreator.getDiscountDB();
        this.myInventoryDAO = externalSysCreator.getInventory();
        this.myRegister = new Register();
        this.myReceiptPrinter = new ReceiptPrinter();
    }

    /**
     * Starts a new sale by initializing a new Sale instance.
     */
    public void startNewSale()
    {
        this.myCurrentSale = new Sale();
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
    public LastSaleUpdateDTO addItem(int itemId, int quantity)
    {
        if (myCurrentSale.contains(itemId))
        {
            return myCurrentSale.increaseItemQuantity(itemId, quantity);
        }
        else
        {
            Item itemToAdd = new Item(myInventoryDAO.fetchItem(itemId), quantity);
            return myCurrentSale.addItem(itemToAdd);
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
        Discount itemDiscount = new Discount(myDiscountDb.findDiscountByShopList(shopList));
        appliedDiscounts.add(myCurrentSale.applyDiscount(itemDiscount));
        double totalPrice = myCurrentSale.getTotalPrice();
        Discount totalPriceDiscount = new Discount(myDiscountDb.findDiscountByTotalSum(totalPrice));
        appliedDiscounts.add(myCurrentSale.applyDiscount(totalPriceDiscount));
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
        AppliedDiscountDTO appliedDiscount;
        Discount customerDiscount = new Discount(myDiscountDb.findDiscountByCustomerId(customerId));
        appliedDiscount = myCurrentSale.applyDiscount(customerDiscount);
        return appliedDiscount;
    }

    /**
     * Processes a payment for the current sale.
     *
     * @param amount The amount paid by the customer.
     * @return The change returned to the customer.
     */
    public double pay(double amount)
    {
        SaleDTO saleInfo = myCurrentSale.getSaleDTO();
        PaymentDTO payment = new Payment(amount);
        myInventoryDAO.updateInventory(saleInfo.shoplist());
        myAccountingSys.updateAccountingSystem(saleInfo);
        myRegister.increaseAmount(payment.getPaidAmount());
        ReceiptDTO receipt = payment.getReceipt(saleInfo);
        double change = payment.calculateChange(saleInfo.totalPrice());
        myRegister.decreaseAmount(change);
        myReceiptPrinter.printReceipt(receipt);
        return change;
    }
}
