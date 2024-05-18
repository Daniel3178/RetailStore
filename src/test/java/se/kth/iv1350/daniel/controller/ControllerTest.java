package se.kth.iv1350.daniel.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.daniel.controller.exceptions.ConnectionFailed;
import se.kth.iv1350.daniel.integration.inventory_db.inventory_exc.DatabaseConnectionFailed;
import se.kth.iv1350.daniel.integration.inventory_db.inventory_exc.ItemDoesNotExist;
import se.kth.iv1350.daniel.model.dto.LastSaleUpdateDTO;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest
{
    private Controller instanceToTest;

    @BeforeEach
    void setUp()
    {
        try
        {

            instanceToTest = new Controller();
        }
        catch (IOException ioException)
        {
            System.out.println("Something went wrong in the io");
        }

    }

    @AfterEach
    void tearDown()
    {
        instanceToTest = null;
    }

    @Test
    void startNewSale()
    {
        assertDoesNotThrow(() -> instanceToTest, "There was a problem starting a new sale");
    }

    @Test
    void endSale()
    {
        instanceToTest.startNewSale();
        assertDoesNotThrow(() -> instanceToTest.endSale(), "There was a problem ending a sale");
    }

    @Test
    void addItemExist()
    {
        try
        {
            final int ITEM_ID = 101;
            final int COUNT = 2;
            instanceToTest.startNewSale();
            LastSaleUpdateDTO result = instanceToTest.addItem(ITEM_ID, COUNT);
            int itemIdResult = result.itemDTO().itemId();
            int itemCountResult = result.quantity();
            assertEquals(ITEM_ID, itemIdResult, "Item is not added to sale");
            assertEquals(COUNT, itemCountResult, "Number of added item is not correct");
        }
        catch (ItemDoesNotExist itemExc)
        {
            System.out.println("Test item does not exist");
        }
        catch (ConnectionFailed conExc)
        {
            System.out.println("There has been a problem with connection");
        }
    }

    @Test
    void addItemDoesNotExist()
    {
        final int ITEM_ID = 201;
        final int COUNT = 2;
        instanceToTest.startNewSale();
        assertThrows(
                ItemDoesNotExist.class, () -> instanceToTest.addItem(ITEM_ID, COUNT),
                "Not existed item is added to a sale"
        );
    }

    @Test
    void pay()
    {
        try
        {
            final double ITEM_PRICE = 20.0;
            final double ITEM_VAT_RATE = 0.06;
            final double PAID_AMOUNT = 100.0;
            final int ITEM_ID = 101;
            final int COUNT = 2;
            instanceToTest.startNewSale();
            instanceToTest.addItem(ITEM_ID, COUNT);
            double expectedChangeResult = Math.ceil(PAID_AMOUNT - (ITEM_PRICE * (1 + ITEM_VAT_RATE) * COUNT));
            double changeResult = Math.ceil(instanceToTest.pay(100.0));
            instanceToTest.endSale();
            assertEquals(expectedChangeResult, changeResult, "The change amount is not correct");
        }

        catch (ItemDoesNotExist itemExc)
        {
            System.out.println("Item test does not exist in the database");
        }
        catch (ConnectionFailed conExc)
        {
            System.out.println("There has been a problem connecting to database");
        }
    }
}