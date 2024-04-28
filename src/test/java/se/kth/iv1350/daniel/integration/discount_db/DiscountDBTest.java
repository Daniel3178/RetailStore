package se.kth.iv1350.daniel.integration.discount_db;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscountDBTest
{

    private DiscountDB instanceToTest;
    @BeforeEach
    void setUp()
    {
        instanceToTest = new DiscountDB();
    }

    @AfterEach
    void tearDown()
    {
        instanceToTest = null;
    }

    @Test
    void calculateReducedAmountNullArgumentException()
    {
        assertDoesNotThrow(()->instanceToTest.calculateReducedAmount(null), "It does not handle null argument exception ");
    }

//    @Test
//    void findDiscountByCustomerId()
//    {
//    }
//
//    @Test
//    void findDiscountByTotalSum()
//    {
//    }
}