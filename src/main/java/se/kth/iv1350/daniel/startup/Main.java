package se.kth.iv1350.daniel.startup;

import se.kth.iv1350.daniel.controller.Controller;
import se.kth.iv1350.daniel.integration.ExternalSysCreator;
import se.kth.iv1350.daniel.view.View;

import java.io.IOException;

public class Main
{
    public static void main(String[] args)
    {
        try
        {

            View view = new View(new Controller());
//            for (int i = 0; i < 4; i++)
//            {
                view.runSampleTest(3500);
//            }
        }
        catch (IOException ioExc)
        {
            System.out.println("Something went wrong with IO");
        }
    }
}
