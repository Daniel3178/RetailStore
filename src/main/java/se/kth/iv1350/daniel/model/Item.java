package se.kth.iv1350.daniel.model;

import se.kth.iv1350.daniel.model.dto.ItemDTO;

public class Item
{
    private final ItemDTO itemDTO;
    private int quantity;
    public Item(ItemDTO itemInfo, Integer quantity)
    {
        this.itemDTO = itemInfo;
        this.quantity = quantity == null ? 1 : quantity;
    }

    public ItemDTO getItemInfo()
    {
        return itemDTO;
    }

    public void setQuantity(int quantity)
    {
        this.quantity += quantity;
    }
    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(itemDTO.toString());
        stringBuilder.append("\n x");
        stringBuilder.append(this.quantity);
        stringBuilder.append('\n');
        return stringBuilder.toString();
    }
}
