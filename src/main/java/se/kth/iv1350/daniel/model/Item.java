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

    public void setQuantity(int quantity)
    {
            this.quantity += quantity;
    }
    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(myItemDTO.toString());
        stringBuilder.append("\n x");
        stringBuilder.append(this.quantity);
        stringBuilder.append('\n');
        return stringBuilder.toString();
    }
}
