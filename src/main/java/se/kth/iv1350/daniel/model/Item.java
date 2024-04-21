package se.kth.iv1350.daniel.model;

import se.kth.iv1350.daniel.model.dto.ItemDTO;

public class Item
{
    private final ItemDTO myItemDTO;
    private int quantity;
    public Item(ItemDTO itemInfo, int quantity)
    {
        this.myItemDTO = itemInfo;
        this.quantity =  quantity;
    }

    public ItemDTO getItemInfo()
    {
        return myItemDTO;
    }

    public int getQuantity()
    {
        return this.quantity;
    }
    public int getItemId()
    {
        return myItemDTO.itemId();
    }
    public double getItemPrice()
    {
        return myItemDTO.price();
    }
    public double getItemVat()
    {
        return myItemDTO.vatRate();
    }

    /**
     * Increases the current quantity by a number
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
        stringBuilder.append(myItemDTO.toString()).append("\n").append(this.quantity).append("\n");
        return stringBuilder.toString();
    }
}
