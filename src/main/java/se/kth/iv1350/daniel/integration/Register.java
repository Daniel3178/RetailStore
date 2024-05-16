package se.kth.iv1350.daniel.integration;

public class Register
{
    /**
     * Total money in the register
     */
    private double currentAmount;

    public Register()
    {
        currentAmount = 0;
    }

    /**
     * Adds the sum that customer has paid to the register
     * @param paidAmount: contains the paid amount sum
     */
    public void increaseAmount(double paidAmount)
    {
        this.currentAmount += paidAmount;
        System.out.printf("[!]\tRegister increased with customer's paid money: %.2f SEK\n", paidAmount);
    }

    /**
     * Subtracts the change that should be returned to the customer
     * @param changeAmount: contains the change amount
     */
    public void decreaseAmount(double changeAmount)
    {
        currentAmount -= changeAmount;
        System.out.printf("[!]\tRegister decreased with change money returned to customer: %.2f SEK\n", changeAmount);
        System.out.printf("[!]\tRegister has currently: %.2f SEK\n", this.currentAmount);
    }

    public double getCurrentAmount()
    {
        return currentAmount;
    }

}
