package se.kth.iv1350.daniel.model.dto;

public record ReceiptDTO(SaleDTO saleInfo, double amountPaid, double changeAmount)
{
    public void print()
    {
        System.out.printf("total price: %-5f" ,
                          saleInfo.totalPrice()
        );
        System.out.println("Receipt Printed");
    }
}
