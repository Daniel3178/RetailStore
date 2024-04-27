package se.kth.iv1350.daniel.integration.inventory_db;
import se.kth.iv1350.daniel.model.dto.ItemDTO;
import java.util.List;

public class InventoryDAO
{
    public ItemDTO fetchItem(int itemId)
    {
        return Inventory.getInstance().findItemById(itemId);
    }

    public void updateInventory(List<ItemDTO> allItems)
    {
        System.out.println("[!]\tInventory is updated");
        Inventory.getInstance().updateInventory(allItems);
    }
}
