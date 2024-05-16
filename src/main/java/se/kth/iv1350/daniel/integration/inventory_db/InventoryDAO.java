package se.kth.iv1350.daniel.integration.inventory_db;

import se.kth.iv1350.daniel.model.dto.ItemDTO;

import java.util.List;

public class InventoryDAO
{
    /**
     * Fetches an item from the inventory based on the provided item ID.
     *
     * @param itemId The ID of the item to fetch.
     * @return The ItemDTO object representing the fetched item.
     */
    public ItemDTO fetchItem(int itemId)
    {
        return Inventory.getInstance().findItemById(itemId);
    }

    /**
     * Updates the inventory with the provided list of items.
     *
     * @param allItems The list of items to update the inventory with.
     */
    public void updateInventory(List<ItemDTO> allItems)
    {
        System.out.println("[!]\tInventory is updated");
        for (ItemDTO item : allItems)
        {
            System.out.println(
                    "[!]\tItem with ID: " + item.itemId() + " , count: " + item.quantity() + " is removed from inventory.");
        }
        Inventory.getInstance().updateInventory(allItems);
    }
}
