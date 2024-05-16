package se.kth.iv1350.daniel.integration.inventory_db.inventory_exc;

public class ItemDoesNotExist extends Exception
{
    int itemId;
    public ItemDoesNotExist(int itemId)
    {
        super("The scanned item with ID: " + itemId + " does not exist in the inventory.");
        this.itemId = itemId;
    }

    public int getNonExistedItemId()
    {
        return this.itemId;
    }

}
