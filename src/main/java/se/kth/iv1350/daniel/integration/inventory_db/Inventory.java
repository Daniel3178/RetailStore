package se.kth.iv1350.daniel.integration.inventory_db;
import se.kth.iv1350.daniel.model.dto.ItemDTO;
import se.kth.iv1350.daniel.model.dto.ItemDescriptionDTO;
import java.io.*;
import java.util.*;
import static se.kth.iv1350.daniel.integration.inventory_db.ItemConstants.*;

class Inventory
{
    /**
     * The path where all the data on items exist and it will be written
     */
    private final String PATH = "src/main/resources/";
    private static Inventory instance;
    private Map<Integer, ItemDTO> myCurrentData;

    /**
     * Private constructor is used for implementing a Singletone design pattern
     */
    private Inventory() {
        myCurrentData = new HashMap<>();
        loadData();
    }

    static Inventory getInstance() {
        if (instance == null) {
            instance = new Inventory();
        }
        return instance;
    }

    /**
     * It searches for the item with a specific id in myCurrentData and uses the info found there to create a dto
     * object
     * @param itemId: id to lookup in myCurrentData
     * @return: itemDTO with the required information
     */
    ItemDTO findItemById(int itemId)
    {
        return getItemInfo(myCurrentData.get(itemId));
    }

    /**
     * It looks up each item in myCurrentData based on itemId and updates the quantity.
     * @param shoplist: list of items in the sale that should be subtracted from the inventory
     */
    void updateInventory(List<ItemDTO> shoplist){
        for(ItemDTO eachItem: shoplist)
        {
            ItemDTO info = myCurrentData.get(eachItem.itemId());
            int quantity = info.quantity();
            quantity -= eachItem.quantity();
            ItemDTO modifiedItem = new ItemDTO(info.price(), info.vatRate(), info.itemId(), info.descDTO(), quantity);
            myCurrentData.replace(eachItem.itemId(), modifiedItem);
        }
//        saveData();
    }

    /**
     * It reads all data on items and stores it in myCurrentData as a key-value pair where the key is the itemID
     * and the value is an array of strings where each string is a name, price, vatRate etc
     */
    private void loadData()
    {
        LinkedList<String> allLines = new LinkedList<>();
        Map<Integer, ItemDTO> allItems = new HashMap<>();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader( PATH + "inventory_data"));
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                allLines.add(line);
            }
            reader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("Failed Loading");
        }
        finally
        {
            for (String line : allLines)
            {
                String[] parts = line.split(";");
                int itemId = Integer.parseInt(parts[0]);
                List<String> itemDetails = Arrays.asList(parts).subList(1, parts.length);
                ItemDTO item = getItemDTO(itemDetails, itemId);
                allItems.put(itemId, item);
            }
            this.myCurrentData = allItems;
        }
    }

    private ItemDTO getItemInfo(ItemDTO item)
    {
        if (item == null)
        {
            return null;
        }
        return new ItemDTO(item.price(), item.vatRate(), item.itemId(), item.descDTO());
    }
    private ItemDTO getItemDTO(List<String> itemDetails, int itemId)
    {
        ItemDescriptionDTO description = new ItemDescriptionDTO(
                itemDetails.get(NAME.getIndex()),
                itemDetails.get(DESCRIPTION.getIndex()),
                itemDetails.get(EXPIRATION_DATE.getIndex()),
                itemDetails.get(CATEGORY.getIndex()),
                itemDetails.get(SUPPLIER.getIndex())
        );
        return new ItemDTO(
                Double.parseDouble(itemDetails.get(PRICE.getIndex())),
                Double.parseDouble(itemDetails.get(VAT_RATE.getIndex())),
                itemId, description,
                Integer.parseInt(itemDetails.get(QUANTITY.getIndex()))
        );
    }

    private List<String> getItemDetails(ItemDTO item)
    {
        List<String> itemDetails = new ArrayList<>(Collections.nCopies(8, null));
        itemDetails.set(NAME.getIndex(), item.descDTO().name());
        itemDetails.set(DESCRIPTION.getIndex(), item.descDTO().description());
        itemDetails.set(EXPIRATION_DATE.getIndex(), item.descDTO().expirationDate());
        itemDetails.set(CATEGORY.getIndex(), item.descDTO().category());
        itemDetails.set(SUPPLIER.getIndex(), item.descDTO().supplier());
        itemDetails.set(PRICE.getIndex(), Double.toString(item.price()));
        itemDetails.set(VAT_RATE.getIndex(), Double.toString(item.vatRate()));
        itemDetails.set(QUANTITY.getIndex(), Integer.toString(item.quantity()));
        return itemDetails;
    }

    /**
     * It writes the data found in myCurrentData in a new file called inventory_data_UPDATED.txt.
     */
    private void saveData()
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(PATH +"inventory_data_UPDATED.txt"));
            for (Map.Entry<Integer, ItemDTO> entry : myCurrentData.entrySet())
            {
                int itemId = entry.getKey();
                ItemDTO item = entry.getValue();
                List<String> itemDetails = getItemDetails(item);
                StringBuilder lineBuilder = new StringBuilder();
                lineBuilder.append(itemId);
                for (String detail : itemDetails)
                {
                    lineBuilder.append(";").append(detail);
                }
                writer.write(lineBuilder.toString());
                writer.newLine();
            }
            writer.close();
        }
        catch (IOException e)
        {
            System.err.println("ERROR writing data to file.");
        }
    }
}
