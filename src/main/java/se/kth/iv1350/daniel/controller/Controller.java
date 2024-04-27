package se.kth.iv1350.daniel.controller;

import se.kth.iv1350.daniel.integration.ExternalSysCreator;
import se.kth.iv1350.daniel.integration.ReceiptPrinter;
import se.kth.iv1350.daniel.integration.Register;
import se.kth.iv1350.daniel.integration.accounting_system.AccountingSystem;
import se.kth.iv1350.daniel.integration.discount_db.DiscountDB;
import se.kth.iv1350.daniel.integration.inventory_db.InventoryDAO;
import se.kth.iv1350.daniel.model.Item;
import se.kth.iv1350.daniel.model.Payment;
import se.kth.iv1350.daniel.model.Sale;
import se.kth.iv1350.daniel.model.SaleLog;
import se.kth.iv1350.daniel.model.dto.*;

import java.util.ArrayList;
import java.util.List;


public class Controller
{
    Sale myCurrentSale;
    AccountingSystem myAccountingSys;
    DiscountDB myDiscountDb;
    InventoryDAO myInventoryDAO;
    Register myRegister;
    ReceiptPrinter myReceiptPrinter;

    /**
     * Initializes a new Controller instance with dependencies from ExternalSysCreator.
     * @param externalSysCreator An instance of ExternalSysCreator providing external systems.
     */
    public Controller(ExternalSysCreator externalSysCreator) {
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
    public void endSale()
    {
        SaleLog.getInstance().addSale(this.myCurrentSale.getSaleInfo());
        this.myCurrentSale = null;
    }

    /**
     * Adds an item to the current sale or increases its quantity if already added.
     * @param itemId The ID of the item to add.
     * @param quantity The quantity of the item to add.
     * @return LastSaleUpdateDTO containing information about the updated sale.
     */
    public LastSaleUpdateDTO addItem(int itemId, int quantity)
    {
        if(myCurrentSale.contains(itemId))
        {
            return myCurrentSale.increaseQuantity(itemId, quantity);
        }
        else
        {
            ItemDTO itemDTO = myInventoryDAO.fetchItem(itemId);
            return myCurrentSale.addItem(itemDTO, quantity);
        }
    }

    /**
     * Applies discounts on the current sale based on items and total price.
     * @return List of AppliedDiscountDTOs containing information about applied discounts.
     */
    public List<AppliedDiscountDTO> applyDiscountsOnSale()
    {
        List<AppliedDiscountDTO> appliedDiscounts = new ArrayList<>();
        List<ItemDTO> shopList = myCurrentSale.getShopList();
        DiscountDTO itemDiscount = myDiscountDb.calculateReducedAmount(shopList);
        appliedDiscounts.add(myCurrentSale.applyDiscount(itemDiscount));
        double totalPrice = myCurrentSale.getTotalPrice();
        DiscountDTO totalPriceDiscount = myDiscountDb.findDiscountByTotalSum(totalPrice);
        appliedDiscounts.add(myCurrentSale.applyDiscount(totalPriceDiscount));
        return appliedDiscounts;
    }

    public AppliedDiscountDTO applyDiscountByCustomerId(int customerId)
    {
        AppliedDiscountDTO appliedDiscount;
        DiscountDTO customerDiscount = myDiscountDb.findDiscountByCustomerId(customerId);
        appliedDiscount = myCurrentSale.applyDiscount(customerDiscount);
        return appliedDiscount;
    }


    public double pay(double amount)
    {
        SaleDTO saleInfo = myCurrentSale.getSaleInfo();
        PaymentDTO payment = new Payment(amount, saleInfo);
        myInventoryDAO.updateInventory(saleInfo.shoplist());
        myAccountingSys.updateAccountingSystem(saleInfo);
        myRegister.registerPayment(payment);
        ReceiptDTO receipt = payment.getReceipt(saleInfo);
        myRegister.decreaseAmount(payment);
        myReceiptPrinter.printReceipt(receipt);
        return payment.getChangeAmount();
    }

}
