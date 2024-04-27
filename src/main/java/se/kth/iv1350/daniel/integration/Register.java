package se.kth.iv1350.daniel.integration;
import se.kth.iv1350.daniel.model.dto.PaymentDTO;

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
     * @param payment: contains the paid amount sum
     */
    public void registerPayment(PaymentDTO payment)
    {
        this.currentAmount += payment.getPaidAmount();
        System.out.printf("[!]\tRegister increased with customer's paid money: %.2f SEK\n", payment.getPaidAmount());
    }

    /**
     * Subtracts the change that should be returned to the customer
     * @param payment: contains the change amount
     */
    public void decreaseAmount(PaymentDTO payment)
    {
        currentAmount -= payment.getChangeAmount();
        System.out.printf("[!]\tRegister decreased with change money returned to customer: %.2f SEK\n", payment.getChangeAmount());
        System.out.printf("[!]\tRegister has currently: %.2f SEK\n", this.currentAmount);
    }

    public double getCurrentAmount()
    {
        return currentAmount;
    }

}
