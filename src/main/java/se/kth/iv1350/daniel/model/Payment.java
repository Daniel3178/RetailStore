package se.kth.iv1350.daniel.model;

import se.kth.iv1350.daniel.model.dto.ReceiptDTO;
import se.kth.iv1350.daniel.model.dto.SaleDTO;

public class Payment
{
    private final double paidAmount;

    public Payment(double amount)
    {
        this.paidAmount = amount;
    }

    public double getPaidAmount()
    {
        return this.paidAmount;
    }

    /**
     * Exception: It should alter if the change is negative i.e. customer has not paid fully.
     * @param totalPrice: the total price of the sale.
     * @return: the amount that should be returned to customer
     */
    private double calculateChange(double totalPrice)
    {
        assert paidAmount - totalPrice >= 0: "Customer has not paid fully";
        return paidAmount - totalPrice;
    }

    /**
     * Task: provides the required information for a receipt
     * @param saleInfo: Sale information required in the receipt such as price, vat, applied disc etc.
     * @return: receipt object that put all info in a specific format.
     */
    public ReceiptDTO getReceipt(SaleDTO saleInfo)
    {
        return new ReceiptDTO(saleInfo, this.paidAmount, calculateChange(saleInfo.totalPrice()));
    }

}
