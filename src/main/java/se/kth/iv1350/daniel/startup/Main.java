package se.kth.iv1350.daniel.startup;

import se.kth.iv1350.daniel.controller.Controller;
import se.kth.iv1350.daniel.integration.ExternalSysCreator;
import se.kth.iv1350.daniel.model.SaleLog;
import se.kth.iv1350.daniel.view.View;

public class Main
{
    public static void main(String[] args)
    {
        View view = new View(new Controller(new ExternalSysCreator()));
        view.runSampleTest(200);
        view.runSampleTest(300);
        view.runSampleTest(400);

        SaleLog.getInstance();

    }
}
