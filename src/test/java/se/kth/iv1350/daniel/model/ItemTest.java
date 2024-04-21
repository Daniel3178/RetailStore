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
        assertTrue(
                myItem.getQuantity() == increasedNumber + BEFORE_INCREMENT,
                "IncreaseQuantity method does not handle positive numbers"
        );
    }

    @Test
    void increaseQuantityByZero()
    {
        int increasedNumber = 0;
        myItem.increaseQuantity(increasedNumber);
        assertTrue(myItem.getQuantity() == BEFORE_INCREMENT, "IncreasedQuantity method does not handle zero");
    }

    @Disabled
    @Test
    void increaseQuantityByNegativeNumber()
    {
        int increasedNumber = -10;
        myItem.increaseQuantity(increasedNumber);
        assertTrue(
                myItem.getQuantity() == BEFORE_INCREMENT, "IncreasedQuantity method does not handle negative numbers");
    }
}