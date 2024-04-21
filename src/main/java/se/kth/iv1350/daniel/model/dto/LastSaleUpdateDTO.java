package se.kth.iv1350.daniel.model.dto;

public record LastSaleUpdateDTO(ItemDTO itemDTO, int quantity, double totalPrice, double totalVat)
{
    @Override
    public String toString()
    {
        String formattedTotalPrice = String.format("%.2f", totalPrice);
        String formattedTotalVat = String.format("%.2f", totalVat);

        StringBuilder sb = new StringBuilder();
        sb.append("[*]\tItem Information:\n").append(itemDTO);
        sb.append("[*]\tQuantity: ").append(quantity).append("\n\n");
        sb.append("[*]\tTotal Price: ").append(formattedTotalPrice).append(" SEK\n");
        sb.append("[*]\tTotal VAT: ").append(formattedTotalVat).append(" SEK\n");
        return sb.toString();
    }
}
