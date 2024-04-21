package se.kth.iv1350.daniel.integration.inventory_db;
import se.kth.iv1350.daniel.model.Item;
import se.kth.iv1350.daniel.model.dto.ItemDTO;
import se.kth.iv1350.daniel.model.dto.ItemDescriptionDTO;
import java.io.*;
import java.util.*;
import static se.kth.iv1350.daniel.integration.inventory_db.ItemConstants.*;

class Inventory
{
    /**
     * The path where all the data on items exist or it will be written
     */
    private final String PATH = "src/main/java/se/kth/iv1350/daniel/integration/inventory_db/";
    private static Inventory instance;
    private Map<Integer, List<String>> myCurrentData;

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
        List<String> info = myCurrentData.get(itemId);
        if (info != null && !info.isEmpty())
        {
            ItemDescriptionDTO description = new ItemDescriptionDTO(
                    info.get(NAME.getIndex()),
                    info.get(DESCRIPTION.getIndex()),
                    info.get(EXPIRATION_DATE.getIndex()),
                    info.get(CATEGORY.getIndex()),
                    info.get(SUPPLIER.getIndex())
            );
            return new ItemDTO(
                    Double.parseDouble(info.get(PRICE.getIndex())),
                    Double.parseDouble(info.get(VAT_RATE.getIndex())),
                    itemId, description
            );
        }
        return null;
    }

    /**
     * It looks up each item in myCurrentData based on itemId and updates the quantity.
     * @param shoplist: list of items in the sale that should be subtracted from the inventory
     */
    void updateInventory(List<Item> shoplist){
        for(Item eachItem: shoplist)
        {
            List<String> info = myCurrentData.get(eachItem.getItemId());
            int quantity = Integer.parseInt(info.get(QUANTITY.getIndex()));
            quantity -= eachItem.getQuantity();
            info.set(QUANTITY.getIndex(), Integer.toString(quantity));
            myCurrentData.replace(eachItem.getItemId(), info);
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
        Map<Integer, List<String>> allItems = new HashMap<>();
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
                allItems.put(itemId, itemDetails);
            }
            this.myCurrentData = allItems;
        }
    }

    /**
     * It writes the data found in myCurrentData in a new file called inventory_data_UPDATED.txt.
     */
    private void saveData()
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(PATH +"inventory_data_UPDATED.txt"));
            for (Map.Entry<Integer, List<String>> entry : myCurrentData.entrySet())
            {
                int itemId = entry.getKey();
                List<String> itemDetails = entry.getValue();
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
