package se.kth.iv1350.daniel.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.daniel.controller.Controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ViewTest
{

    private View instanceToTest;
    private ByteArrayOutputStream inMemPrintOut;
    private PrintStream originalSysOut;

    @BeforeEach
    void setUp()
    {
        Controller ctrl = null;
        try
        {
            ctrl = new Controller();
            instanceToTest = new View(ctrl);

        }
        catch (IOException e)
        {
            System.out.println("Something went wrong with IO");
            ;
        }

        inMemPrintOut = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(inMemPrintOut);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
    }

    @AfterEach
    void tearDown()
    {
        instanceToTest = null;
        inMemPrintOut = null;
        System.setOut(originalSysOut);
    }

    @Test
    void runSampleTest()
    {
        instanceToTest.runSampleTest(4000);
        boolean result = true;
        String printOut = inMemPrintOut.toString();
        List<String> expectedOutputs = List.of("Cashier should return", "Discount Type", "Item Information",
                                               "SCREEN NOTIFICATION", "Total Price", "Total VAT");

        for (String eachOutput : expectedOutputs)
        {
            if (!printOut.contains(eachOutput))
            {
                result = false;
                break;
            }
        }
        assertTrue(result, "UI did not start correctly");
    }

}