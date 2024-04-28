package se.kth.iv1350.daniel.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.daniel.model.dto.ItemDTO;
import se.kth.iv1350.daniel.model.dto.ItemDescriptionDTO;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest
{
    Item myItem;
    final int BEFORE_INCREMENT = 1;


    @BeforeEach
    void setUp()
    {
        ItemDescriptionDTO description = new ItemDescriptionDTO(
                "bananas", "ripe banana", "2024-06-01", "Fruit", "Agriculture Inc");
        myItem = new Item(new ItemDTO(20, 0.06, 1, description), BEFORE_INCREMENT);

    }

    @AfterEach
    void tearDown()
    {
        myItem = null;
    }

    @Test
    void increaseQuantityByPositiveNumber()
    {
        int increasedNumber = 10;
        myItem.increaseQuantity(increasedNumber);
        assertEquals(myItem.getQuantity(), increasedNumber + BEFORE_INCREMENT,
                     "IncreaseQuantity method does not handle positive numbers"
        );
    }

    @Test
    void increaseQuantityByZero()
    {
        final int ZERO = 0;
        assertDoesNotThrow(()->myItem.increaseQuantity(ZERO), "IncreasedQuantity method does not handle adding zero");
    }

    @Test
    void calculatePriceInclusiveVatPositiveQuantity()
    {
        final int QUANTITY = 1;
        double result = myItem.calculatePriceInclusiveVat(QUANTITY);
        double expected = myItem.getItemPrice() * QUANTITY* (1+myItem.getItemVat());
        assertEquals(expected, result, "Calculation of price inclusive vat is wrong");
    }

    @Test
    void calculatePriceInclusiveVatNegativeQuantity()
    {
        final int NEGATIVE_QUANTITY = -1;
        assertDoesNotThrow(()->myItem.calculateVatAmount(NEGATIVE_QUANTITY), "Negative quantity is not handled");
    }

    @Test
    void calculatePriceInclusiveVatZeroQuantity()
    {
        final int ZERO_QUANTITY = 0;
        assertDoesNotThrow(()->myItem.calculateVatAmount(ZERO_QUANTITY), "Zero quantity is not handled");
    }

    @Test
    void calculateVatAmountPositiveQuantity()
    {
        final int QUANTITY = 1;
        double result = myItem.calculateVatAmount(QUANTITY);
        double expected = myItem.getItemPrice() * QUANTITY* myItem.getItemVat();
        assertEquals(expected, result, "Calculation of vat amount is wrong");
    }

    @Test
    void calculateVatAmountNegativeQuantity()
    {
        final int NEGATIVE_QUANTITY = -1;
        assertDoesNotThrow(()->myItem.calculateVatAmount(NEGATIVE_QUANTITY), "Negative quantity is not handled");
    }

    @Test
    void calculateVatAmountZeroQuantity()
    {
        final int ZERO_QUANTITY = 0;
        assertDoesNotThrow(()->myItem.calculateVatAmount(ZERO_QUANTITY), "Zero quantity is not handled");
    }

    @Disabled
    @Test
    void increaseQuantityByNegativeNumber()
    {
        final int NEGATIVE_NUMBER = -10;
        assertDoesNotThrow(()->
                myItem.increaseQuantity(NEGATIVE_NUMBER), "IncreasedQuantity method does not handle negative numbers");
    }
}