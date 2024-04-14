package se.kth.iv1350.daniel.model.dto;

public record ReceiptDTO(SaleDTO saleInfo, double amountPaid, double changeAmount)
{
    public String generateReceipt()
    {
        return String.format("total price: %.2f | change returned: %.2f", saleInfo.totalPrice(), changeAmount);
    }
}
