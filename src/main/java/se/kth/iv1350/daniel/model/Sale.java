package se.kth.iv1350.daniel.model;

import se.kth.iv1350.daniel.model.dto.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Sale
{
    private static int saleId = 0;
    private double myTotalPrice;
    private final String MY_CURRENT_DATE;
    private double myTotalVat;
    private final List<AppliedDiscountDTO> MY_DISCOUNTS;
    private final List<Item> MY_SHOPLIST;

    public Sale()
    {
        this.MY_DISCOUNTS = new ArrayList<>();
        this.MY_SHOPLIST = new ArrayList<>();
        this.myTotalVat = 0;
        this.myTotalPrice = 0;
        LocalDateTime now = LocalDateTime.now();
        this.MY_CURRENT_DATE = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        saleId += 1;
    }

    /**
     * Task: To check whether an item exist in the current sale
     *
     * @param itemId: ID to search for the item in the list
     * @return: true if exist otherwise false
     */
    public boolean contains(int itemId)
    {
        return MY_SHOPLIST.stream().anyMatch(item -> item.getItemId() == itemId);
    }

    /**
     * @return: all the items in the current sale
     */
    public List<ItemDTO> getShopList()
    {
        List<ItemDTO> shopList = new ArrayList<>();
        for (Item item : this.MY_SHOPLIST)
        {
            shopList.add(item.getItemDTO());
        }
        return shopList;
    }

    /**
     * Task: increase the number of a specific item in the list
     *
     * @param itemId:   Is used to find the specific item
     * @param quantity: Number of item to add
     * @return: The description of the item that has been added and the updated total price
     */
    public LastSaleUpdateDTO increaseQuantity(int itemId, int quantity)
    {
        assert quantity > 0 : "Quantity should be > 0";
        for (Item item : MY_SHOPLIST)
        {
            if (item.getItemId() == itemId)
            {
                item.increaseQuantity(quantity);
                myTotalPrice += item.calculatePriceInclusiveVat(quantity);
                myTotalVat += item.calculateVatAmount(quantity);
                return new LastSaleUpdateDTO(item.getItemDTO(), quantity, myTotalPrice, myTotalVat);
            }
        }
        return null;
    }

    /**
     * Task: To add a new item in the shopping list
     *
     * @param itemToAdd: Item that will be added to sale
     * @return: The description of the item that has been added and the updated total price
     */
    public LastSaleUpdateDTO addItem(Item itemToAdd)
    {
        this.MY_SHOPLIST.add(itemToAdd);
        myTotalPrice += itemToAdd.getPriceInclusiveVat();
        myTotalVat += itemToAdd.getVatAmount();
        return new LastSaleUpdateDTO(itemToAdd.getItemDTO(), itemToAdd.getQuantity(), myTotalPrice, myTotalVat);
    }

    public double getTotalPrice()
    {
        return this.myTotalPrice;
    }

    /**
     * Exception: It should not return null! Task: It applies a discount, updates the total price and stores the
     * discount as applied discount for the sale report
     *
     * @param discount: contains type and description, here we use type to specify how a discount should be applied
     * @return: An object that tells what kind of discount, the reduced amount from total price and the updated total
     * price after the discount has been applied
     */
    public AppliedDiscountDTO applyDiscount(Discount discount)
    {
        AppliedDiscountDTO applied = discount.applyDiscount(this.myTotalPrice);
        this.myTotalPrice = applied.updatedTotalPrice();
        this.MY_DISCOUNTS.add(applied);
        return applied;
    }

    /**
     * @return: a dto object of the sale
     */
    public SaleDTO getSaleDTO()
    {
        return new SaleDTO(
                saleId, this.getShopList(), this.myTotalPrice, this.myTotalVat, this.MY_CURRENT_DATE,
                this.MY_DISCOUNTS
        );
    }

}
