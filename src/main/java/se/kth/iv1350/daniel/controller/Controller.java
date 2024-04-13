package se.kth.iv1350.daniel.controller;

import se.kth.iv1350.daniel.integration.ExternalSysCreator;
import se.kth.iv1350.daniel.integration.ReceiptPrinter;
import se.kth.iv1350.daniel.integration.Register;
import se.kth.iv1350.daniel.integration.accounting_system.AccountingSystem;
import se.kth.iv1350.daniel.integration.discount_db.DiscountDB;
import se.kth.iv1350.daniel.integration.inventory_db.Inventory;
import se.kth.iv1350.daniel.model.Item;
import se.kth.iv1350.daniel.model.Payment;
import se.kth.iv1350.daniel.model.Sale;
import se.kth.iv1350.daniel.model.dto.*;

import java.util.ArrayList;
import java.util.List;

public class Controller
{
    Sale myCurrentSale;
    AccountingSystem myAccountingSys;
    DiscountDB myDiscountDb;
    Inventory myInventory;
    Register myRegister;
    ReceiptPrinter myReceiptPrinter;
    // Sale currentSale = null
    // Customer currentSale = null
    // public void start sale() ::- currentSale = new Sale() ...

    public Controller(ExternalSysCreator externalSysCreator) {
        this.myAccountingSys = externalSysCreator.getAccountingSystem();
        this.myDiscountDb = externalSysCreator.getDiscountDB();
        this.myInventory = externalSysCreator.getInventory();
        this.myRegister = new Register();
        this.myReceiptPrinter = new ReceiptPrinter();
    }




    public void startNewSale()
    {
        this.myCurrentSale = new Sale();
    }
    public void endSale()
    {
        this.myCurrentSale = null;
    }

    /**
     * It is view's responsibility to not send null as quantity!!
     * @param itemId
     * @param quantity
     * @return
     */
    public LastSaleUpdateDTO addItem(int itemId, int quantity)
    {
        if(myCurrentSale.contains(itemId))
        {
            return myCurrentSale.updateQuantity(itemId, quantity);
        }
        else
        {
            //Inventory inv = Inventory.getInstance();
            ItemDTO itemDTO = myInventory.fetchItem(itemId);
            return myCurrentSale.addItem(itemDTO, quantity);
        }
    }


    public List<AppliedDiscountDTO> applyDiscountsOnSale()
    {
        List<AppliedDiscountDTO> appliedDiscounts = new ArrayList<>();
        List<Item> shopList = myCurrentSale.getShopList();
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


    public void pay(double amount)
    {
        Payment payment = new Payment(amount);
        SaleDTO saleInfo = myCurrentSale.getSaleInfo();
        myInventory.updateInventory(saleInfo.shoplist());
        myAccountingSys.updateAccountingSystem(saleInfo);
        myRegister.registerPayment(payment);
        ReceiptDTO receipt = payment.getReceipt(saleInfo);
        myReceiptPrinter.printReceipt(receipt);
    }

//    public ReceiptDTO pay(Double amount)
//    {
//        Payment customerPayment = new Payment(amount);
//        Inventory.getInstance().updateInventory(currentSale.getCustomerShopList());
//        //AccountingSystem.getInstance().updateAccountingSystem(currentSale.getSaleInfo());
//        customerPayment.register();
//        return customerPayment.getReceipt(currentSale.getSaleInfo());
//    }

}
