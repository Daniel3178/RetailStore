package se.kth.iv1350.daniel.view;

import se.kth.iv1350.daniel.model.SaleObserver;

public class TotalRevenueView implements SaleObserver
{
    private double totalIncome;
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
