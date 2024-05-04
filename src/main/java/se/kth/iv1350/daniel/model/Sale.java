package se.kth.iv1350.daniel.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.daniel.model.dto.AppliedDiscountDTO;
import se.kth.iv1350.daniel.model.dto.ItemDTO;
import se.kth.iv1350.daniel.model.dto.LastSaleUpdateDTO;
import se.kth.iv1350.daniel.model.dto.SaleDTO;

public class Sale
{
    private static int saleId = 0;
    private double myTotalPrice;
    private final String myCurrentDate;
    private double myTotalVat;
    private final List<AppliedDiscountDTO> myDiscounts;
    private final List<Item> myShopList;

    public Sale()
    {
        this.myDiscounts = new ArrayList<>();
        this.myShopList = new ArrayList<>();
        this.myTotalVat = 0;
        this.myTotalPrice = 0;
        LocalDateTime now = LocalDateTime.now();
        this.myCurrentDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        saleId += 1;
    }

    /**
     * Task: To check whether an item exist in the current sale
     *
     * @param itemId: ID to search for the item in the list
     * @return true if exist otherwise false
     */
    public boolean contains(int itemId)
    {
        return myShopList.stream().anyMatch(item -> item.getItemId() == itemId);
    }

    /**
     * Task: increase the number of a specific item in the list
     *
     * @param itemId:   Is used to find the specific item
     * @param quantity: Number of item to add
     * @return The description of the item that has been added and the updated total price
     */
    public LastSaleUpdateDTO increaseQuantity(int itemId, int quantity)
    {
        for (Item item : myShopList)
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
     * @return The description of the item that has been added and the updated total price
     */
    public LastSaleUpdateDTO addItem(Item itemToAdd)
    {
        this.myShopList.add(itemToAdd);
        myTotalPrice += itemToAdd.getPriceInclusiveVat();
        myTotalVat += itemToAdd.getVatAmount();
        return new LastSaleUpdateDTO(itemToAdd.getItemDTO(), itemToAdd.getQuantity(), myTotalPrice, myTotalVat);
    }

    /**
     * Exception: It should not return null! Task: It applies a discount, updates the total price and stores the
     * discount as applied discount for the sale report
     *
     * @param discount: contains type and description, here we use type to specify how a discount should be applied
     * @return An object that tells what kind of discount, the reduced amount from total price and the updated total
     * price after the discount has been applied
     */
    public AppliedDiscountDTO applyDiscount(Discount discount)
    {
        AppliedDiscountDTO applied = discount.applyDiscount(this.myTotalPrice);
        this.myTotalPrice = applied.updatedTotalPrice();
        this.myDiscounts.add(applied);
        return applied;
    }

    /**
     * @return all the items in the current sale
     */
    public List<ItemDTO> getShopList()
    {
        List<ItemDTO> shopList = new ArrayList<>();
        for (Item item : this.myShopList)
        {
            shopList.add(item.getItemDTO());
        }
        return shopList;
    }
    public double getTotalPrice()
    {
        return this.myTotalPrice;
    }
    /**
     * @return a dto object of the sale
     */
    public SaleDTO getSaleDTO()
    {
        return new SaleDTO(
                saleId, this.getShopList(), this.myTotalPrice, this.myTotalVat, this.myCurrentDate,
                this.myDiscounts
        );
    }

}
