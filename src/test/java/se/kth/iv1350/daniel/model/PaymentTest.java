package se.kth.iv1350.daniel.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest
{
    private Payment instanceToTest;
    private final double PAID_AMOUNT = 1000;
    @BeforeEach
    void setUp()
    {
        instanceToTest = new Payment(PAID_AMOUNT);
    }

    @AfterEach
    void tearDown()
    {
        instanceToTest = null;
    }

    @Test
    void calculateChange()
    {
        final double TOTAL_SUM = 1000;
        double change = instanceToTest.calculateChange(TOTAL_SUM);
        assertEquals(0, change, "Change calculation does not function properly");
    }
}