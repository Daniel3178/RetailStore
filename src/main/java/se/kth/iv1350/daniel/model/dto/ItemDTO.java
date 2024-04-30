package se.kth.iv1350.daniel.model.dto;

public record ItemDTO( double price, double vatRate, int itemId, ItemDescriptionDTO descDTO, int quantity)
{
    public ItemDTO(double price, double vatRate, int itemId, ItemDescriptionDTO descDTO)
    {
        this(price, vatRate, itemId, descDTO, 0);
    }
    public double getItemPriceInclVat()
    {
        return price * ( 1 + vatRate);
    }
}
