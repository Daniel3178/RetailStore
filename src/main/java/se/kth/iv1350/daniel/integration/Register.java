package se.kth.iv1350.daniel.integration;

import se.kth.iv1350.daniel.model.Payment;

public class Register
{
    private double currentAmount;

    public Register()
    {
        currentAmount = 0;
    }

//    public static Register getInstance()
//    {
//        if (currentRegister == null)
//        {
//            currentRegister = new Register();
//        }
//        return currentRegister;
//    }

    public void registerPayment(Payment payment)
    {
        this.currentAmount += payment.getAmount();
        System.out.println("Register increased with some money: " + payment.getAmount());
    }
//    public void increaseAmount(double amount)
//    {
//
//        currentAmount += amount;
//        System.out.println("Register increased with some money: " + amount);
//    }

    public void decreaseAmount(double amount)
    {
        currentAmount -= amount;
        System.out.println("Register decreased with some money: " + this.currentAmount);
    }

    public double getCurrentAmount()
    {
        return currentAmount;
    }

}
