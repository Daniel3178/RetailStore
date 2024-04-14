package se.kth.iv1350.daniel.model.dto;

public record ReceiptDTO(SaleDTO saleInfo, double amountPaid, double changeAmount)
{
    public void generateReceipt()
    {
        System.out.printf("total price: %.2f | change returned: %.2f\n",
                          saleInfo.totalPrice(),
                          changeAmount
        );
    }
}
