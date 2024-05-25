package se.kth.iv1350.daniel.startup;

import se.kth.iv1350.daniel.controller.Controller;
import se.kth.iv1350.daniel.startup.compostion_vs_inheritance.CustomRandomComposition;
import se.kth.iv1350.daniel.startup.compostion_vs_inheritance.CustomRandomInheritance;
import se.kth.iv1350.daniel.view.View;

import java.io.IOException;

public class Main
{
    public static void main(String[] args)
    {
//        retailStoreSampleRun();
        compositionVsInheritanceSampleRun();
    }

    private static void retailStoreSampleRun()
    {
        try
        {
            View view = new View(new Controller());
            view.runSampleTest(3500);
        }
        catch (IOException ioExc)
        {
            System.out.println("Something went wrong with IO");
        }
    }

    private static void compositionVsInheritanceSampleRun()
    {
        CustomRandomInheritance.runSample();
        CustomRandomComposition.runSample();
    }

}
