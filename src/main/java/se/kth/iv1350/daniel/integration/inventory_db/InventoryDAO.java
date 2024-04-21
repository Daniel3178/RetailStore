package se.kth.iv1350.daniel.integration.inventory_db;

import se.kth.iv1350.daniel.model.Item;
import se.kth.iv1350.daniel.model.dto.ItemDescriptionDTO;
import se.kth.iv1350.daniel.model.dto.ItemDTO;

import java.util.List;
import java.util.Map;

public class InventoryDAO
{
    public ItemDTO fetchItem(int itemId)
    {
        return Inventory.getInstance().findItemById(itemId);
    }

    public void updateInventory(List<Item> allItems)
    {
        System.out.println("[!]\tInventory is updated");
        Inventory.getInstance().updateInventory(allItems);
    }
}
