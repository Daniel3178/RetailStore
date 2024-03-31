package se.kth.iv1350.daniel.model;

import se.kth.iv1350.daniel.model.dto.ItemDTO;

import java.util.ArrayList;
import java.util.List;

public class Customer
{
    private static Customer currentCustomer = null;
    Integer customerId = null;
    private List<Item> shopList;
    private Customer(){
        this.shopList = new ArrayList<>();
    }

    public static Customer getInstance()
    {
        if (currentCustomer == null)
        {
            currentCustomer = new Customer();
        }
        return currentCustomer;
    }

    public void setCustomerId(Integer customerId)
    {
        this.customerId = customerId;
    }

    public Integer getCustomerId()
    {
        return this.customerId;
    }

    public List<Item> getShopList()
    {
        return this.shopList;
    }
    public void addItem(ItemDTO itemInfo, Integer quantity)
    {
        this.shopList.add(new Item(itemInfo, quantity));
    }

    public boolean isItemExist(int itemId)
    {
        for(Item item : shopList)
        {
            if (item.getItemInfo().itemId() == itemId)
            {
                return true;
            }
        }
        return false;
    }

    public void updateQuantity(int itemId, Integer quantity)
    {
        for(Item item : shopList)
        {
            if (item.getItemInfo().itemId() == itemId)
            {
                item.setQuantity(quantity);
                break;
            }
        }
    }



}
