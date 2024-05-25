package se.kth.iv1350.daniel.startup;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


class MainTest
{

    private ByteArrayOutputStream inMemPrintOut;
    private PrintStream originalSysOut;

    @BeforeEach
    void setUp()
    {
        inMemPrintOut = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(inMemPrintOut);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
    }

    @AfterEach
    void tearDown()
    {
        inMemPrintOut = null;
        System.setOut(originalSysOut);
    }

    @Test
    void main()
    {
        Main.main(null);
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