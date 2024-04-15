package se.kth.iv1350.daniel.model.dto;

public record ItemDTO( double price, double vatRate, int itemId, ItemDescriptionDTO descDTO)
{
    @Override
    public String toString()
    {
        String formatedPriceInclVat = String.format("%.2f", price * (1 + vatRate));
        StringBuilder sb = new StringBuilder();
        sb.append("ItemID: ").append(itemId).append("\n");
        sb.append("Price inclusive VAT: ").append(formatedPriceInclVat).append("\n");
        sb.append("VAT Rate: ").append(vatRate*100).append(" %\n");
        sb.append(descDTO.toString());
        return sb.toString();
    }
}
