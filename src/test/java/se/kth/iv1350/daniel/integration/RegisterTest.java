package se.kth.iv1350.daniel.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class RegisterTest
{
    private Register instanceToRegister;
    private ByteArrayOutputStream inMemPrintOut;
    private PrintStream originalSysOut;
    @BeforeEach
    void setUp()
    {
        instanceToRegister = Register.getInstance();
        inMemPrintOut = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(inMemPrintOut);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
    }

    @AfterEach
    void tearDown()
    {
        instanceToRegister = null;
        inMemPrintOut = null;
        System.setOut(originalSysOut);
    }

    @Test
    void registerPaymentPositiveAmount()
    {
        final double POSITIVE_AMOUNT = 10.00;
        double before = instanceToRegister.getCurrentAmount();
        instanceToRegister.increaseAmount(POSITIVE_AMOUNT);
        double after = instanceToRegister.getCurrentAmount();
        double result = after - before;
        assertEquals(POSITIVE_AMOUNT, result, "Positive amount is not registered");
    }

    @Test
    void registerPaymentNegativeAmount()
    {
        final double NEGATIVE_AMOUNT = -10.00;
        assertDoesNotThrow(()->instanceToRegister.increaseAmount(NEGATIVE_AMOUNT), "Negative amount is not handled");
    }

    @Test
    void registerPaymentZeroAmount()
    {
        final double ZERO_AMOUNT = 0.00;
        double before = instanceToRegister.getCurrentAmount();
        instanceToRegister.increaseAmount(ZERO_AMOUNT);
        double after = instanceToRegister.getCurrentAmount();
        double result = after - before;
        assertEquals(ZERO_AMOUNT, result, "Zero amount is not registered");
    }

    @Test
    void decreaseAmountPositiveAmount()
    {
        final double POSITIVE_AMOUNT = 10.00;
        double before = instanceToRegister.getCurrentAmount();
        instanceToRegister.decreaseAmount(POSITIVE_AMOUNT);
        double after = instanceToRegister.getCurrentAmount();
        double result = before - after;
        assertEquals(POSITIVE_AMOUNT, result, "Positive amount is not registered");
    }

    @Test
    void updateRegisterByIncreasing()
    {
        final double POSITIVE_AMOUNT = 10.00;
        instanceToRegister.increaseAmount(POSITIVE_AMOUNT);
        String printOut = inMemPrintOut.toString();
        String expectedOutput = "Register increased with";
        assertTrue(printOut.contains(expectedOutput), "Register is not updated");
    }

    @Test
    void updateRegisterByDecreasing()
    {
        final double POSITIVE_AMOUNT = 10.00;
        instanceToRegister.decreaseAmount(POSITIVE_AMOUNT);
        String printOut = inMemPrintOut.toString();
        String expectedOutput = "Register decreased with";
        assertTrue(printOut.contains(expectedOutput), "Register is not updated");
    }

    @Test
    void decreaseAmountZeroAmount()
    {
        final double ZERO_AMOUNT = 0.00;
        double before = instanceToRegister.getCurrentAmount();
        instanceToRegister.decreaseAmount(ZERO_AMOUNT);
        double after = instanceToRegister.getCurrentAmount();
        double result = before - after;
        assertEquals(ZERO_AMOUNT, result, "Zero amount is not registered");
    }

    @Test
    void decreaseAmountNegativeAmount()
    {
        final double NEGATIVE_AMOUNT = -10.00;
        assertDoesNotThrow(()->instanceToRegister.decreaseAmount(NEGATIVE_AMOUNT), "Negative amount is not handled");
    }
}