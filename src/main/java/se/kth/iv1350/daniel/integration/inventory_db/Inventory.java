package se.kth.iv1350.daniel.integration.inventory_db;

import se.kth.iv1350.daniel.model.dto.DescriptionDTO;
import se.kth.iv1350.daniel.model.dto.ItemDTO;
import se.kth.iv1350.daniel.model.dto.PriceDTO;

public class Inventory
{
    private static Inventory inventory = null;

    private Inventory()
    {

    }

    public static Inventory getInstance()
    {
        if (inventory == null)
        {
            inventory = new Inventory();
        }
        return inventory;
    }

    public ItemDTO fetchItem(int itemId)
    {
        return new ItemDTO(0.23, "Tomato", 1,
                           new DescriptionDTO("Italy", "Today"),
                           new PriceDTO("SEK", 12.0));
    }
}
