package se.kth.iv1350.daniel.model.dto;

public record LastSaleUpdateDTO(ItemDTO itemDTO, int quantity, double totalPrice, double totalVat)
{
    @Override
    public String toString()
    {
        String formattedTotalPrice = String.format("%.2f", totalPrice);
        String formattedTotalVat = String.format("%.2f", totalVat);

        StringBuilder sb = new StringBuilder();
        sb.append("Item Information:\n").append(itemDTO).append("\n");
        sb.append("Quantity: ").append(quantity).append("\n");
        sb.append("Total Price: ").append(formattedTotalPrice).append("\n");
        sb.append("Total VAT: ").append(formattedTotalVat).append("\n");
        return sb.toString();
    }
}
