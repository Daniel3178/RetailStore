package se.kth.iv1350.daniel.model.dto;

public record ItemDTO(double vatRate, String name, int itemId, DescriptionDTO descDTO, PriceDTO priceDTO)
{
    public double getPriceAmount()
    {
        return priceDTO.amount();
    }

    public String getPriceCurrency()
    {
        return priceDTO.currency();
    }

    public String getDescription()
    {
        return descDTO.toString();
    }

    public String getPrice()
    {
        return priceDTO.toString();
    }

    @Override
    public String toString()
    {
        return String.format("Item Name: %-20s \n Count: %-2d \n Description: %s | Price: %-4d %-10s" +
                                     "| IID: %-5d | TY.: %-10s | BR.: %-10s | PR.: $%-10s",
                             this.name(),
                             this
        );
    }
}
