package se.kth.iv1350.daniel.view;

import se.kth.iv1350.daniel.model.SaleObserver;

public class TotalRevenueView extends SaleObserver
{

    @Override
    protected void doShowTotalIncome() throws Exception
    {

        String formattedTotalIncome = String.format("%.2f", super.totalIncome);
        System.out.println("[SCREEN NOTIFICATION] Total Revenue Screen is updated, total income increased to: " + formattedTotalIncome + " SEK");
    }

    @Override
    protected void handleErrors(Exception e)
    {
        System.out.println("There was a problem displaying the total income ");
        e.printStackTrace();
    }
}
