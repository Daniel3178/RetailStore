package se.kth.iv1350.daniel.model;

public abstract class SaleObserver
{
    protected double totalIncome;

    public void updateTotalIncome(double priceOfTheSaleThatWasJustMade)
    {
        calculateTotalIncome(priceOfTheSaleThatWasJustMade);
        showTotalIncome();
    }

    private void calculateTotalIncome(double totalPrice)
    {
        this.totalIncome += totalPrice;
    }

    private void showTotalIncome()
    {
        try
        {
            doShowTotalIncome();
        }
        catch (Exception e)
        {
            handleErrors(e);
        }
    }

    protected abstract void doShowTotalIncome() throws Exception;

    protected abstract void handleErrors(Exception e);
}
