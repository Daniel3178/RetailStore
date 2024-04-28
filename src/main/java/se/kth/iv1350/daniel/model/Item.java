package se.kth.iv1350.daniel.model;

import se.kth.iv1350.daniel.model.dto.ItemDTO;
import se.kth.iv1350.daniel.model.dto.ItemDescriptionDTO;

public class Item
{
    private final double PRICE;
    private final double VAT_RATE;
    private final int ITEM_ID;
    private final ItemDescriptionDTO DESC_DTO;
    private int quantity;

    public Item(ItemDTO itemInfo, int quantity)
    {
        this.PRICE = itemInfo.price();
        this.VAT_RATE = itemInfo.vatRate();
        this.ITEM_ID = itemInfo.itemId();
        this.DESC_DTO = itemInfo.descDTO();
        this.quantity = quantity;
    }

    public ItemDTO getItemDTO()
    {
        return new ItemDTO(PRICE, VAT_RATE, ITEM_ID, DESC_DTO, quantity);
    }

    public int getQuantity()
    {
        return this.quantity;
    }

    public int getItemId()
    {
        return this.ITEM_ID;
    }

    /**
     * Calculates the price inclusive of VAT for a given quantity of items.
     *
     * @param itemQuantity The quantity of items.
     * @return The total price inclusive of VAT.
     */
    public double calculatePriceInclusiveVat(int itemQuantity)
    {
        return PRICE * itemQuantity * (1 + VAT_RATE);
    }

    /**
     * Calculates the VAT amount for a given quantity of items.
     *
     * @param itemQuantity The quantity of items.
     * @return The VAT amount.
     */
    public double calculateVatAmount(int itemQuantity)
    {
        return PRICE * itemQuantity * VAT_RATE;
    }

    public double getItemPrice()
    {
        return this.PRICE;
    }
    public double getItemVat()
    {
        return this.VAT_RATE;
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

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getItemDTO().toString()).append("\n").append(this.quantity).append("\n");
        return stringBuilder.toString();
    }
}
