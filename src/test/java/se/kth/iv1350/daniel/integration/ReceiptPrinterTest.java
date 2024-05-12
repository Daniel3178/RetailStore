package se.kth.iv1350.daniel.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.daniel.model.dto.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptPrinterTest
{
    private ReceiptPrinter instanceToTest;
    private ByteArrayOutputStream inMemPrintOut;
    private PrintStream originalSysOut;
    @BeforeEach
    void setUp()
    {
        instanceToTest = ReceiptPrinter.getInstance();
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
    void printReceipt()
    {
        List<ItemDTO> itemList = new ArrayList<>();
        ItemDescriptionDTO description = new ItemDescriptionDTO(
                "bananas", "ripe banana", "2024-06-01", "Fruit", "Agriculture Inc");
        itemList.add(new ItemDTO(20, 0.06, 1, description));

        List<AppliedDiscountDTO> discList = new ArrayList<>();
        discList.add(new AppliedDiscountDTO(new DiscountDTO(
                30.0, DiscountType.AMOUNT, "Item Discount",
                "This discount is based on shop list"
        ), 20.0, 100.0));
        SaleDTO saleInfo = new SaleDTO(1, itemList,200, 20,"TODAY",discList);
        ReceiptDTO arg = new ReceiptDTO(saleInfo, 20, 10);
        instanceToTest.printReceipt(arg);
        String printOut = inMemPrintOut.toString();
        String expectedOutput = "PRINTING RECEIPT";
        assertTrue(printOut.contains(expectedOutput), "Printer is not working");
    }

//    @Disabled
    @Test
    void printReceiptNullArg()
    {
        assertThrows(Exception.class,()->instanceToTest.printReceipt(null), "Null argument is not handled");
    }
}