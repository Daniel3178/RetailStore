package se.kth.iv1350.daniel.controller;

import se.kth.iv1350.daniel.integration.discount_db.DiscountDB;
import se.kth.iv1350.daniel.integration.inventory_db.Inventory;
import se.kth.iv1350.daniel.model.Customer;
import se.kth.iv1350.daniel.model.Item;
import se.kth.iv1350.daniel.model.Sale;
import se.kth.iv1350.daniel.model.dto.ItemDTO;

import java.util.List;

public class Controller
{
    private static Controller ctr = null;

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

    public void addItem(int itemId, Integer quantity)
    {
        Customer currentCustomer = Customer.getInstance();
        if(currentCustomer.isItemExist(itemId))
        {
            currentCustomer.updateQuantity(itemId, quantity);
        }
        else
        {
            Inventory inv = Inventory.getInstance();
            ItemDTO itemDTO = inv.fetchItem(itemId);
            currentCustomer.addItem(itemDTO, quantity);
        }
    }

    public void applyDiscount(Integer customerId)
    {
        Customer currentCustomer = Customer.getInstance();
        currentCustomer.setCustomerId(customerId);
        List<Item> allItems = currentCustomer.getShopList();
        DiscountDB discountDB = DiscountDB.getInstance();
        double reducedAmount = discountDB.calculateReducedAmount(allItems);
        Sale currentSale = Sale.getInstance();

    }

}
