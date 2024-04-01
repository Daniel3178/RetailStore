package se.kth.iv1350.daniel.model;

import se.kth.iv1350.daniel.model.dto.ReceiptDTO;
import se.kth.iv1350.daniel.model.dto.SaleDTO;

public class Payment
{
    private double amount;

    public Payment(double amount)
    {
        this.amount= amount;
    }

    public double getAmount()
    {
        return this.amount;
    }

    private double calculateChange(double totalPrice)
    {
        return amount - totalPrice;
    }

    public void register()
    {
        Register register = Register.getInstance();
        register.increaseAmount(this.amount);
    }

    public ReceiptDTO getReceipt(SaleDTO saleInfo)
    {
        double change = calculateChange(saleInfo.totalPrice());
        Register.getInstance().decreaseAmount(change);
        return new ReceiptDTO(saleInfo, this.amount, change);
    }
}
