package se.kth.iv1350.daniel.model;

public class Register
{
    private double currentAmount;
    private static Register currentRegister = null;
    private Register()
    {
        currentAmount = 0;
    }

    public static Register getInstance()
    {
        if (currentRegister == null)
        {
            currentRegister = new Register();
        }
        return currentRegister;
    }

    public void increaseAmount(double amount)
    {

        currentAmount += amount;
        System.out.println("Register increased with some money: " + amount);
    }

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
