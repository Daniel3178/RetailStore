package se.kth.iv1350.daniel.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.daniel.model.Payment;
import se.kth.iv1350.daniel.model.dto.PaymentDTO;

import static org.junit.jupiter.api.Assertions.*;

class RegisterTest
{
    private Register instanceToRegister;
    @BeforeEach
    void setUp()
    {
        instanceToRegister = new Register();
    }

    @AfterEach
    void tearDown()
    {
        instanceToRegister = null;
    }

    @Test
    void registerPaymentPositiveAmount()
    {
        final double POSITIVE_AMOUNT = 10.00;
        double before = instanceToRegister.getCurrentAmount();
        instanceToRegister.registerPayment(POSITIVE_AMOUNT);
        double after = instanceToRegister.getCurrentAmount();
        double result = after - before;
        assertEquals(POSITIVE_AMOUNT, result, "Positive amount is not registered");
    }

    @Test
    void registerPaymentNegativeAmount()
    {
        final double NEGATIVE_AMOUNT = -10.00;
        assertDoesNotThrow(()->instanceToRegister.registerPayment(NEGATIVE_AMOUNT), "Negative amount is not handled");
    }

    @Test
    void registerPaymentZeroAmount()
    {
        final double ZERO_AMOUNT = 0.00;
        double before = instanceToRegister.getCurrentAmount();
        instanceToRegister.registerPayment(ZERO_AMOUNT);
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