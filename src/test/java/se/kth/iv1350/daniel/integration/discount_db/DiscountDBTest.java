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
        instanceToTest = DiscountDB.getInstance();
    }

    @AfterEach
    void tearDown()
    {
        instanceToTest = null;
    }

    @Test
    void calculateReducedAmountNullArgumentException()
    {
        assertDoesNotThrow(()->instanceToTest.findDiscountByShopList(null), "It does not handle null argument exception ");
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