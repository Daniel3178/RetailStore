package se.kth.iv1350.daniel.model;

import se.kth.iv1350.daniel.model.dto.PaymentDTO;
import se.kth.iv1350.daniel.model.dto.ReceiptDTO;
import se.kth.iv1350.daniel.model.dto.SaleDTO;

public class Payment implements PaymentDTO
{
    private final double PAID_AMOUNT;
    public Payment(double amount)
    {
        this.PAID_AMOUNT = amount;
    }

    /**
     * Exception: It should alter if the change is negative i.e. customer has not paid fully.
     * @param totalPrice: the total price of the sale.
     * @return the amount that should be returned to customer
     */
    @Override
    public double calculateChange(double totalPrice)
    {
        assert PAID_AMOUNT - totalPrice >= 0: "Customer has not paid fully";
        return PAID_AMOUNT - totalPrice;
    }

    /**
     * Task: provides the required information for a receipt
     * @param saleInfo: Sale information required in the receipt such as price, vat, applied disc etc.
     * @return receipt object that put all info in a specific format.
     */
    @Override
    public ReceiptDTO getReceipt(SaleDTO saleInfo)
    {
        return new ReceiptDTO(saleInfo, this.PAID_AMOUNT, calculateChange(saleInfo.totalPrice()));
    }
    @Override
    public double getPaidAmount()
    {
        return this.PAID_AMOUNT;
    }
}
