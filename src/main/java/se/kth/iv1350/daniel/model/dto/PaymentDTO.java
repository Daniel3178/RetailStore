package se.kth.iv1350.daniel.model.dto;

public interface PaymentDTO
{
    double getPaidAmount();
    double getChangeAmount();
    ReceiptDTO getReceipt(SaleDTO saleInfo);
}
