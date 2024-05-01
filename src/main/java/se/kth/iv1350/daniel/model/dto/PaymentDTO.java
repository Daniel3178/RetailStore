package se.kth.iv1350.daniel.model.dto;

public interface PaymentDTO
{
    double getPaidAmount();
//    double getChangeAmount();
    double calculateChange(double totalPrice);
    ReceiptDTO getReceipt(SaleDTO saleInfo);
}
