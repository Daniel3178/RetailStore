package se.kth.iv1350.daniel.startup;

import se.kth.iv1350.daniel.controller.Controller;
import se.kth.iv1350.daniel.view.View;

public class Main
{
    public static void main(String[] args)
    {
        View view = new View(new Controller());
        view.runSampleTest(3500);
    }
}
