package se.kth.iv1350.daniel.model;
import se.kth.iv1350.daniel.model.dto.*;
import java.util.ArrayList;
import java.util.List;

public class Sale
{

    private double myTotalPrice;
    private final String myCurrentDate;
    private double myTotalVat;
    private final List<AppliedDiscountDTO> myDiscounts;
    private final List<Item> myItems;

    public Sale()
    {
        this.myDiscounts = new ArrayList<>();
        this.myItems = new ArrayList<>();
        this.myTotalVat = 0;
        this.myTotalPrice = 0;
        this.myCurrentDate = "TODAY";
    }


    /**
     * Task: To check whether an item exist in the current sale
     * @param itemId: ID to search for the item in the list
     * @return: true if exist otherwise false
     */
    public boolean contains(int itemId)
    {
        for(Item item: myItems)
        {
            if (item.getItemInfo().itemId() == itemId)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * @return: all the items in the current sale
     */
    public List<Item> getShopList()
    {
        return this.myItems;
    }

    /**
     * Task: increase the number of a specific item in the list
     * @param itemId: Is used to find the specific item
     * @param quantity: Number of item to add
     * @return: The description of the item that has been added and the updated total price
     */
    public LastSaleUpdateDTO updateQuantity(int itemId, int quantity)
    {
        assert quantity > 0: "Quantity should be > 0";
        for (Item item : myItems)
        {
            if (item.getItemInfo().itemId() == itemId)
            {
                item.increaseQuantity(quantity);
                myTotalPrice += item.getItemInfo().price() * quantity *(1 + item.getItemInfo().vatRate());
                myTotalVat += item.getItemInfo().price() * quantity * item.getItemInfo().vatRate();
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
        this.myItems.add(new Item(itemDTO, quantity));
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
     * Task: It applies a discount, updates the total price and stores the discount for the sale report
     * @param discount: contains type and description, here we use type to specify how a discount should be applied
     * @return: A summary that specifies what type of discount has been applied and how much the price has been reduced
     */
    public AppliedDiscountDTO applyDiscount(DiscountDTO discount)
    {
        switch (discount.discountTypeDTO().discountType().getAmountType()){
            case AMOUNT: {
                myTotalPrice -= discount.discountTypeDTO().value();
                AppliedDiscountDTO discountInSale = new AppliedDiscountDTO(discount,discount.discountTypeDTO().value(),
                myTotalPrice);
                myDiscounts.add(discountInSale);
                return discountInSale;
            }
            case PRECENT: {
                double tempReducedAmount = myTotalPrice * discount.discountTypeDTO().value();
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
        return new SaleDTO(this.myItems, this.myTotalPrice, this.myTotalVat, this.myCurrentDate, this.myDiscounts);
    }

}
