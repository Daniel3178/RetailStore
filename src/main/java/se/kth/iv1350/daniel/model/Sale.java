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
    private final String myCurrentDate;
    private double myTotalVat;
    private final List<AppliedDiscountDTO> myDiscounts;
    private final List<Item> myShoplist;

    public Sale()
    {
        this.myDiscounts = new ArrayList<>();
        this.myShoplist = new ArrayList<>();
        this.myTotalVat = 0;
        this.myTotalPrice = 0;
        LocalDateTime now = LocalDateTime.now();
        this.myCurrentDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        saleId += 1;
    }


    /**
     * Task: To check whether an item exist in the current sale
     * @param itemId: ID to search for the item in the list
     * @return: true if exist otherwise false
     */
    public boolean contains(int itemId)
    {
        return myShoplist.stream().anyMatch(item -> item.getItemId() == itemId);
    }

    /**
     * @return: all the items in the current sale
     */
    public List<Item> getShopList()
    {
        return this.myShoplist;
    }

    /**
     * Task: increase the number of a specific item in the list
     * @param itemId: Is used to find the specific item
     * @param quantity: Number of item to add
     * @return: The description of the item that has been added and the updated total price
     */
    public LastSaleUpdateDTO increaseQuantity(int itemId, int quantity)
    {
        assert quantity > 0: "Quantity should be > 0";
        for (Item item : myShoplist)
        {
            if (item.getItemId() == itemId)
            {
                item.increaseQuantity(quantity);
                myTotalPrice += item.getItemPrice() * quantity *(1 + item.getItemVat());
                myTotalVat += item.getItemPrice() * quantity * item.getItemVat();
                return new LastSaleUpdateDTO(item.getItemInfo(), quantity, myTotalPrice, myTotalVat);
            }
        }
        return null;
    }

    /**
     * Task: To add a new item in the shopping list
     * @param itemDTO: contains the information about the item
     * @param quantity: specifies the number of such item
     * @return: The description of the item that has been added and the updated total price
     */
    public LastSaleUpdateDTO addItem(ItemDTO itemDTO, int quantity)
    {
        assert quantity > 0: "Quantity should be > 0";
        this.myShoplist.add(new Item(itemDTO, quantity));
        myTotalPrice += itemDTO.price() * quantity *(1 + itemDTO.vatRate());
        myTotalVat += itemDTO.price() * quantity * itemDTO.vatRate();
        return new LastSaleUpdateDTO(itemDTO, quantity, myTotalPrice, myTotalVat);
    }


    public double getTotalPrice()
    {
        return this.myTotalPrice;
    }

    /**
     * Exception: It should not return null!
     * Task: It applies a discount, updates the total price and stores the discount as applied discount for the sale report
     * @param discount: contains type and description, here we use type to specify how a discount should be applied
     * @return: An object that tells what kind of discount, the reduced amount from total price and the updated total price
     * after the discount has been applied
     */
    public AppliedDiscountDTO applyDiscount(DiscountDTO discount)
    {
        switch (discount.getAmountType()){
            case AMOUNT: {
                myTotalPrice -= discount.getDiscountValue();
                AppliedDiscountDTO discountInSale = new AppliedDiscountDTO(discount,discount.getDiscountValue(),
                myTotalPrice);
                myDiscounts.add(discountInSale);
                return discountInSale;
            }
            case PRECENT: {
                double tempReducedAmount = myTotalPrice * discount.getDiscountValue();
                myTotalPrice -= tempReducedAmount;
                AppliedDiscountDTO discountInSale = new AppliedDiscountDTO(discount, tempReducedAmount, myTotalPrice);
                myDiscounts.add(discountInSale);
                return discountInSale;
            }
            default: return null;
        }
    }

    public SaleDTO getSaleInfo()
    {
        return new SaleDTO(saleId, this.myShoplist, this.myTotalPrice, this.myTotalVat, this.myCurrentDate, this.myDiscounts);
    }

}
