package se.kth.iv1350.daniel.view;

import se.kth.iv1350.daniel.model.SaleObserver;

import java.io.FileWriter;
import java.io.PrintWriter;

public class TotalRevenueFileOutput extends SaleObserver
{
    @Override
    protected void doShowTotalIncome() throws Exception
    {
        PrintWriter logStream = new PrintWriter(new FileWriter("Observer_Log_File.txt"), true);
        String formattedTotalIncome = String.format("%.2f", super.totalIncome);
        logStream.println("[SCREEN NOTIFICATION] Total Revenue File Output is updated, total income increased to: " + formattedTotalIncome + " SEK");
    }

    @Override
    protected void handleErrors(Exception e)
    {
        System.out.println("CAN NOT LOG.");
        e.printStackTrace();
    }
}
