package se.kth.iv1350.daniel.integration;

import se.kth.iv1350.daniel.model.Payment;

public class Register
{
    private double currentAmount;

    public Register()
    {
        currentAmount = 0;
    }


    public void registerPayment(Payment payment)
    {
        this.currentAmount += payment.getPaidAmount();
        System.out.printf("Register increased with customer's paid money: %.2f SEK\n", payment.getPaidAmount());
    }

    public void decreaseAmount(Payment payment)
    {
        currentAmount -= payment.getChangeAmount();
        System.out.printf("Register decreased with change money returned to customer: %.2f SEK\n", payment.getChangeAmount());
        System.out.printf("Register has currently: %.2f SEK\n", this.currentAmount);
    }

    public double getCurrentAmount()
    {
        return currentAmount;
    }

}
