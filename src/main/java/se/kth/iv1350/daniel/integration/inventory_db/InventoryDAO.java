package se.kth.iv1350.daniel.integration.inventory_db;

import se.kth.iv1350.daniel.integration.inventory_db.inventory_exc.DatabaseConnectionFailed;
import se.kth.iv1350.daniel.integration.inventory_db.inventory_exc.ItemDoesNotExist;
import se.kth.iv1350.daniel.model.dto.ItemDTO;

import java.sql.SQLException;
import java.util.List;

public class InventoryDAO
{
    private static InventoryDAO instance;
    private InventoryDAO ()
    {

    }

    public static InventoryDAO getInstance()
    {
        if (instance == null)
        {
            instance = new InventoryDAO();
        }
        return instance;
    }

    /**
     * Fetches an item from the inventory based on the provided item ID.
     *
     * @param itemId The ID of the item to fetch.
     * @return The ItemDTO object representing the fetched item.
     */
    public ItemDTO fetchItem(int itemId) throws ItemDoesNotExist, DatabaseConnectionFailed
    {
        try
        {
            if (itemId == 111)
            {
                throw new ItemDoesNotExist(itemId);
            }
            return Inventory.getInstance().findItemById(itemId);
        }
        catch (SQLException sqlException)
        {
            throw new DatabaseConnectionFailed();
        }
    }

    /**
     * Updates the inventory with the provided list of items.
     *
     * @param allItems The list of items to update the inventory with.
     */
    public void updateInventory(List<ItemDTO> allItems)
    {
//        System.out.println("[!]\tInventory is updated");
//        for (ItemDTO item : allItems)
//        {
//            System.out.println(
//                    "[!]\tItem with ID: " + item.itemId() + " , count: " + item.quantity() + " is removed from inventory.");
//        }
        Inventory.getInstance().updateInventory(allItems);
    }
}
