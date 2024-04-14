package se.kth.iv1350.daniel.integration.inventory_db;

import se.kth.iv1350.daniel.model.Item;
import se.kth.iv1350.daniel.model.dto.ItemDescriptionDTO;
import se.kth.iv1350.daniel.model.dto.ItemDTO;

import java.util.List;

public class Inventory
{
    public ItemDTO fetchItem(int itemId)
    {
        return new ItemDTO(20.00, 0.06, itemId,
                           new ItemDescriptionDTO("Tomatoe", "Italy", "Tommorow"));
    }

    public void updateInventory(List<Item> allItems)
    {
        System.out.println("Inventory is updated");
    }
}
