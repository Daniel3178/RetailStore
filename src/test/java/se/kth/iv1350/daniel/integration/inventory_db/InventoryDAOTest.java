package se.kth.iv1350.daniel.integration.inventory_db;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.daniel.model.dto.ItemDTO;
import se.kth.iv1350.daniel.model.dto.ItemDescriptionDTO;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryDAOTest
{
    private InventoryDAO instanceToTest;
    private ByteArrayOutputStream inMemPrintOut;
    private PrintStream originalSysOut;


    @BeforeEach
    void setUp()
    {
        instanceToTest = new InventoryDAO();
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
    void fetchItemItemExist()
    {
        final int ITEM_ID = 101;
        ItemDTO result = instanceToTest.fetchItem(ITEM_ID);
        assertEquals(ITEM_ID, result.itemId(), "Item is not found");
    }


    @Test
    void fetchItemItemNoteExist()
    {
        final int ITEM_ID = 201;
        ItemDTO result = instanceToTest.fetchItem(ITEM_ID);
        assertNull(result, "Item does not exist, null should have been returned");
    }

//@Disabled
    @Test
    void updateInventoryNullArgument()
    {
        assertThrows(Exception.class, () -> instanceToTest.updateInventory(null), "Null argument is not handled");

    }


    @Test
    void updateInventory()
    {
        List<ItemDTO> arg = new ArrayList<>();
        arg.add(new ItemDTO(275.99, 0.06, 101,
                            new ItemDescriptionDTO("Red Apples", "Fresh, juicy red apples", "2024-04-20",
                                                   "Fruits", "Agriculture Inc"
                            )
        ));
        instanceToTest.updateInventory(arg);
        String printOut = inMemPrintOut.toString();
        String expectedOutput = "Inventory is updated";
        assertTrue(printOut.contains(expectedOutput), "Inventory is not updated");
    }
}