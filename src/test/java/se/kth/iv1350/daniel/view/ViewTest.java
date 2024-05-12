package se.kth.iv1350.daniel.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.daniel.controller.Controller;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ViewTest
{

    private View instanceToTest;
    private ByteArrayOutputStream inMemPrintOut;
    private PrintStream originalSysOut;
    @BeforeEach
    void setUp()
    {
        Controller ctrl = new Controller();
        instanceToTest = new View(ctrl);

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
        String printOut = inMemPrintOut.toString();
        String expectedOutput = "Cashier should return";
        assertTrue(printOut.contains(expectedOutput), "UI did not start correctly");
    }
}