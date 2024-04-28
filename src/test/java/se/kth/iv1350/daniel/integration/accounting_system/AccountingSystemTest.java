package se.kth.iv1350.daniel.integration.accounting_system;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.daniel.model.dto.SaleDTO;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class AccountingSystemTest
{

    private AccountingSystem instanceToTest;
    private ByteArrayOutputStream inMemPrintOut;
    private PrintStream originalSysOut;
    @BeforeEach
    void setUp()
    {
        instanceToTest = new AccountingSystem();
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
    void updateAccountingSystemNullArgument()
    {
        assertDoesNotThrow(()->instanceToTest.updateAccountingSystem(null), "It does not handle null argument exception");
    }

    @Test
    void updateAccountingSystem()
    {
        instanceToTest.updateAccountingSystem(new SaleDTO(1, null, 20.00, 20.00, "TODAY", null));
        String printOut = inMemPrintOut.toString();
        String expectedOutput = "Accounting System is updated";
        assertTrue(printOut.contains(expectedOutput), "Accounting System is not updated");
    }
}