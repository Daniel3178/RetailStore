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
    @Override
    public String toString()
    {
        String formatedPriceInclVat = String.format("%.2f", price * (1 + vatRate));
        StringBuilder sb = new StringBuilder();
        sb.append("[*]\tItemID: ").append(itemId).append("\n");
        sb.append("[*]\tPrice inclusive VAT: ").append(formatedPriceInclVat).append("\n");
        sb.append("[*]\tVAT Rate: ").append(vatRate*100).append(" %\n");
        sb.append(descDTO.toString());
        return sb.toString();
    }
}
