package se.kth.iv1350.daniel.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.daniel.model.dto.ItemDTO;
import se.kth.iv1350.daniel.model.dto.ItemDescriptionDTO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SaleTest
{

    Sale instanceToTest;
    final int EXISTED_ITEM_ID_ONE = 100;
    final int EXISTED_ITEM_ID_TWO = 101;
    final int NOT_EXISTED_ITEM_ID = 102;
    final int QUANTITY = 20;

    @BeforeEach
    void setUp()
    {
        instanceToTest = new Sale();
        instanceToTest.addItem(new Item( new ItemDTO(275.99, 0.06, EXISTED_ITEM_ID_ONE,
                                           new ItemDescriptionDTO("Red Apples", "Fresh, juicy red apples", "2024-04-20",
                                                                  "Fruits", "Agriculture Inc"
                                           )
        ), QUANTITY));
        instanceToTest.addItem(new Item(new ItemDTO(100.99, 0.06, EXISTED_ITEM_ID_TWO,
                                           new ItemDescriptionDTO("Chocolate Bar", "Milk chocolate bar, 100g",
                                                                  "2024-06-01", "Snacks", "Candy Corp"
                                           )
        ), QUANTITY));
    }

    @AfterEach
    void tearDown()
    {
        instanceToTest = null;
    }

    @Test
    void containsNegativeItemId()
    {
        final int NEGATIVE_ITEM_ID = -1;
        boolean result = instanceToTest.contains(NEGATIVE_ITEM_ID);
        assertFalse(result, "Negative id should be false");
    }

    @Test
    void containsExistedItemId()
    {
        boolean result = instanceToTest.contains(EXISTED_ITEM_ID_ONE);
        assertTrue(result, "Existed item not found");
    }

    @Test
    void containsNotExistedItemId()
    {
        boolean result = instanceToTest.contains(NOT_EXISTED_ITEM_ID);
        assertFalse(result, "Not existed item was found");
    }

    @Test
    void increaseQuantityByPositiveNumber()
    {
        final int LOC_QUANTITY = 1;
        instanceToTest.increaseItemQuantity(EXISTED_ITEM_ID_ONE, LOC_QUANTITY);
        List<ItemDTO> items = instanceToTest.getShopList();
        for (ItemDTO item : items)
        {
            if (item.itemId() == EXISTED_ITEM_ID_ONE)
            {
                int result = item.quantity();
                assertEquals(QUANTITY + LOC_QUANTITY, result);
            }
        }
    }

    @Disabled
    @Test
    void increaseQuantityByNegativeNumber()
    {
        final int NEGATIVE_QUANTITY = -1;
        assertDoesNotThrow(
                () -> instanceToTest.increaseItemQuantity(EXISTED_ITEM_ID_ONE, NEGATIVE_QUANTITY),
                "increaseQuantity does not catch negative number exception"
        );
    }

//    @Disabled
    @Test
    void addItemNullAsItemDTO()
    {
        final int ANY_NUMBER = 10;
        assertThrows(Exception.class, ()->instanceToTest.addItem(new Item(null, ANY_NUMBER)), "addItem does not handle null argument exception");
    }

    @Test
    void addItemNullAsItemDescriptionDTO()
    {
        final int ANY_NUMBER = 10;
        assertDoesNotThrow(()->instanceToTest.addItem(new Item(new ItemDTO(20.0, 0.06, EXISTED_ITEM_ID_ONE, null), ANY_NUMBER)), "addItem does not handle null argument exception");
    }

    @Test
    void applyDiscount()
    {
        // NOT REQUIRED
    }
}