package se.kth.iv1350.daniel.view;

import se.kth.iv1350.daniel.model.SaleObserver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TotalRevenueFileOutput implements SaleObserver
{
    private static final String PATH = "src/main/resources/";
    private double totalIncome;
    private PrintWriter logStream;
    public TotalRevenueFileOutput(){
        try
        {
            logStream = new PrintWriter(new FileWriter(PATH+"log.txt"), true);
        }
        catch (IOException ioException)
        {
            System.out.println("CAN NOT LOG.");
            ioException.printStackTrace();
        }
    }
    @Override
    public void addToIncome(double totalSum)
    {
        this.totalIncome += totalSum;
        updateFileOutput();
    }

    private void updateFileOutput()
    {
        String formattedTotalIncome = String.format("%.2f", totalIncome);
        logStream.println("[!!!] Total Revenue File Output is updated, total income increased to: " + formattedTotalIncome + " SEK");
    }
}
