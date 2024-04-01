package se.kth.iv1350.daniel.controller;

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
    private static Controller ctr = null;
    Sale currentSale = null;

    // Sale currentSale = null
    // Customer currentSale = null
    // public void start sale() ::- currentSale = new Sale() ...

    private Controller() {}

    /**
     * A part of Singletone design pattern
     * @return: the one and only instance of this class
     */
    public static Controller getInstance()
    {
        if (ctr == null)
        {
            ctr = new Controller();
        }
        return ctr;
    }

    public void startNewSale()
    {
        this.currentSale = new Sale();
    }
    public void endSale()
    {
        this.currentSale = null;
    }
    public void addItem(int itemId, Integer quantity)
    {

        if(currentSale.contains(itemId))
        {
            currentSale.updateQuantity(itemId, quantity);
        }
        else
        {
            Inventory inv = Inventory.getInstance();
            ItemDTO itemDTO = inv.fetchItem(itemId);
            currentSale.addItem(itemDTO, quantity);
        }
    }

    public void applyDiscount(Integer customerId)
    {
        currentSale.setCustomerId(customerId);
        List<Item> allItems = currentSale.getCustomerShopList();
        DiscountDB discountDB = DiscountDB.getInstance();
        double reducedAmount = discountDB.calculateReducedAmount(allItems);
        currentSale.decreaseTotalPrice(reducedAmount);
        DiscountRequestDTO discountInfo = currentSale.getDiscountInfo();
        List<DiscountDTO> discountsFound = new ArrayList<>();
        discountsFound.add(DiscountDB.getInstance().findDiscountByCustId(discountInfo.customerId()));
        discountsFound.add(DiscountDB.getInstance().findDiscountByTotalSum(discountInfo.totalSum()));
        currentSale.applyDiscounts(discountsFound);
    }



    public ReceiptDTO pay(Double amount)
    {
        Payment customerPayment = new Payment(amount);
        Inventory.getInstance().updateInventory(currentSale.getCustomerShopList());
        AccountingSystem.getInstance().updateAccountingSystem(currentSale.getSaleInfo());
        customerPayment.register();
        return customerPayment.getReceipt(currentSale.getSaleInfo());
    }

}
