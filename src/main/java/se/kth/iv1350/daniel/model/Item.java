package se.kth.iv1350.daniel.model;

import se.kth.iv1350.daniel.model.dto.ItemDTO;
import se.kth.iv1350.daniel.model.dto.ItemDescriptionDTO;

public class Item
{
    private final double price;
    private final double vatRate;
    private final int itemId;
    private final ItemDescriptionDTO descDTO;
    private int quantity;

    public Item(ItemDTO itemInfo, int quantity)
    {
        this.price = itemInfo.price();
        this.vatRate = itemInfo.vatRate();
        this.itemId = itemInfo.itemId();
        this.descDTO = itemInfo.descDTO();
        this.quantity = quantity;
    }

    /**
     * Increases the current quantity by a number
     *
     * @param quantity: the number that will be added to the current count
     */
    public void increaseQuantity(int quantity)
    {
        this.quantity += quantity;
    }

    /**
     * Calculates the price inclusive of VAT for a given quantity of items.
     *
     * @param itemQuantity The quantity of items.
     * @return The total price inclusive of VAT.
     */
    public double calculatePriceInclusiveVat(int itemQuantity)
    {
        return price * itemQuantity * (1 + vatRate);
    }
    /**
     * Calculates the VAT amount for a given quantity of items.
     *
     * @param itemQuantity The quantity of items.
     * @return The VAT amount.
     */
    public double calculateVatAmount(int itemQuantity)
    {
        return price * itemQuantity * vatRate;
    }
    /**
     * Calculates the price inclusive of VAT for the item.
     *
     * @return The price of the item inclusive of VAT.
     */
    public double getPriceInclusiveVat()
    {
        return price * quantity * (1 + vatRate);
    }
    /**
     * Calculates the VAT amount for the item.
     *
     * @return The amount of VAT for the item.
     */
    public double getVatAmount()
    {
        return price * quantity * vatRate;
    }
    public double getItemPrice()
    {
        return this.price;
    }
    public double getItemVat()
    {
        return this.vatRate;
    }
    public ItemDTO getItemDTO()
    {
        return new ItemDTO(price, vatRate, itemId, descDTO, quantity);
    }
    public int getQuantity()
    {
        return this.quantity;
    }
    public int getItemId()
    {
        return this.itemId;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getItemDTO().toString()).append("\n").append(this.quantity).append("\n");
        return stringBuilder.toString();
    }
}
