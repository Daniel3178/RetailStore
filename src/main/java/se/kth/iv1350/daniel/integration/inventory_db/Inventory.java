package se.kth.iv1350.daniel.integration.inventory_db;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static se.kth.iv1350.daniel.integration.inventory_db.ItemConstants.CATEGORY;
import static se.kth.iv1350.daniel.integration.inventory_db.ItemConstants.DESCRIPTION;
import static se.kth.iv1350.daniel.integration.inventory_db.ItemConstants.EXPIRATION_DATE;
import static se.kth.iv1350.daniel.integration.inventory_db.ItemConstants.NAME;
import static se.kth.iv1350.daniel.integration.inventory_db.ItemConstants.PRICE;
import static se.kth.iv1350.daniel.integration.inventory_db.ItemConstants.QUANTITY;
import static se.kth.iv1350.daniel.integration.inventory_db.ItemConstants.SUPPLIER;
import static se.kth.iv1350.daniel.integration.inventory_db.ItemConstants.VAT_RATE;
import se.kth.iv1350.daniel.model.dto.ItemDTO;
import se.kth.iv1350.daniel.model.dto.ItemDescriptionDTO;

class Inventory
{
    private static Inventory instance;
    private Map<Integer, ItemDTO> myCurrentData;

    /**
     * Private constructor is used for implementing a Singletone design pattern
     */
    private Inventory()
    {
        myCurrentData = new HashMap<>();
        loadData();
    }

    static Inventory getInstance()
    {
        if (instance == null)
        {
            instance = new Inventory();
        }
        return instance;
    }

    /**
     * It searches for the item with a specific id in myCurrentData and uses the
     * info found there to create a dto
     * object
     *
     * @param itemId: id to lookup in myCurrentData
     * @return: itemDTO with the required information
     */
    ItemDTO findItemById(int itemId) throws SQLException
    {
        if(itemId == 113){
            throw new SQLException();
        }
        return getItemInfo(myCurrentData.get(itemId));
    }

    /**
     * It looks up each item in myCurrentData based on itemId and updates the
     * quantity.
     *
     * @param shoplist: list of items in the sale that should be subtracted from the
     *                  inventory
     */
    void updateInventory(List<ItemDTO> shoplist)
    {
        for (ItemDTO eachItem : shoplist)
        {
            ItemDTO info = myCurrentData.get(eachItem.itemId());
            int quantity = info.quantity();
            quantity -= eachItem.quantity();
            ItemDTO modifiedItem = new ItemDTO(info.price(), info.vatRate(), info.itemId(), info.descDTO(), quantity);
            myCurrentData.replace(eachItem.itemId(), modifiedItem);
        }
        saveData();
    }

    /**
     * It reads all data on items and stores it in myCurrentData as a key-value pair
     * where the key is the itemID
     * and the value is an array of strings where each string is a name, price,
     * vatRate etc
     */
    private void loadData()
    {
        LinkedList<String> allLines = new LinkedList<>();
        try
        {
            allLines = readDataFromFile();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("Failed Loading");
        }
        finally
        {
            this.myCurrentData = structureLoadedData(allLines);
        }
    }

    /**
     * Creates a copy of itemDTO that is sent as argument
     *
     * @param item: itemDTO to copy that has the total count in inventory as
     *              quantity
     * @return: a copy that has 1 as quantity.
     */
    private ItemDTO getItemInfo(ItemDTO item)
    {
        if (item == null)
        {
            return null;
        }
        return new ItemDTO(item.price(), item.vatRate(), item.itemId(), item.descDTO());
    }

    private LinkedList<String> readDataFromFile() throws IOException
    {
        LinkedList<String> allLines = new LinkedList<>();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("inventory_data");
        if (inputStream == null)
        {
            throw new IOException("Resource inventory_data not found in classpath");
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                allLines.add(line);
            }
        }
        return allLines;
    }

    private Map<Integer, ItemDTO> structureLoadedData(LinkedList<String> loadedData)
    {
        Map<Integer, ItemDTO> allItems = new HashMap<>();
        for (String line : loadedData)
        {
            String[] parts = line.split(";");
            int itemId = Integer.parseInt(parts[0]);
            List<String> itemDetails = Arrays.asList(parts).subList(1, parts.length);
            ItemDTO item = parseToItemDTO(itemDetails, itemId);
            allItems.put(itemId, item);
        }
        return allItems;
    }

    private ItemDTO parseToItemDTO(List<String> itemDetails, int itemId)
    {
        ItemDescriptionDTO description = new ItemDescriptionDTO(
                itemDetails.get(NAME.getIndex()),
                itemDetails.get(DESCRIPTION.getIndex()),
                itemDetails.get(EXPIRATION_DATE.getIndex()),
                itemDetails.get(CATEGORY.getIndex()),
                itemDetails.get(SUPPLIER.getIndex()));
        return new ItemDTO(
                Double.parseDouble(itemDetails.get(PRICE.getIndex())),
                Double.parseDouble(itemDetails.get(VAT_RATE.getIndex())),
                itemId, description,
                Integer.parseInt(itemDetails.get(QUANTITY.getIndex())));
    }

    private List<String> parseToList(ItemDTO item)
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
     * It writes the data found in myCurrentData in a new file called
     * inventory_data_UPDATED.txt.
     */
    private void saveData()
    {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("inventory_data_UPDATED.txt")))
        {
            for (Map.Entry<Integer, ItemDTO> entry : myCurrentData.entrySet())
            {
                writeLine(writer, entry);
            }
        }
        catch (IOException e)
        {
            System.err.println("ERROR writing data to file.");
        }
    }

    private void writeLine(BufferedWriter writer, Map.Entry<Integer, ItemDTO> entry) throws IOException
    {
        int itemId = entry.getKey();
        ItemDTO item = entry.getValue();
        List<String> itemDetails = parseToList(item);
        StringBuilder lineBuilder = new StringBuilder();
        lineBuilder.append(itemId);
        for (String detail : itemDetails)
        {
            lineBuilder.append(";").append(detail);
        }
        writer.write(lineBuilder.toString());
        writer.newLine();

    }
}
