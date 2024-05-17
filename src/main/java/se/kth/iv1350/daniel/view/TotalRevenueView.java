package se.kth.iv1350.daniel.view;

import se.kth.iv1350.daniel.model.SaleObserver;

public class TotalRevenueView implements SaleObserver
{
    private double totalIncome;

    /**
     * Adds the given amount to the total income and updates the screen.
     *
     * @param totalSum the amount to add to the total income
     */
    @Override
    public void addToIncome(double totalSum)
    {
        this.totalIncome += totalSum;
        updateScreen();
    }

    private void updateScreen() {
        String formattedTotalIncome = String.format("%.2f", totalIncome);
        System.out.println("[!!!] Total Revenue Screen is updated, total income increased to: " + formattedTotalIncome + " SEK");
    }
}
