package se.kth.iv1350.daniel.model.dto;

public record ReceiptDTO(SaleDTO saleInfo, double amountPaid, double changeAmount)
{
    public void generateReceipt()
    {
        System.out.printf("total price: %-5f | change received: %-5f" ,
                          saleInfo.totalPrice(),
                          changeAmount
        );
        System.out.println("Receipt Printed");
    }
}
