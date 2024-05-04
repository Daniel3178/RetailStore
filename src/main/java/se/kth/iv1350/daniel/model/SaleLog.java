package se.kth.iv1350.daniel.model;

import se.kth.iv1350.daniel.model.dto.SaleDTO;

import java.util.ArrayList;
import java.util.List;

public class SaleLog
{
    private static SaleLog instance;
    private final List<SaleDTO> allSale;

    /**
     * Private constructor is used to implement a Singletone design pattern
     */
    private SaleLog()
    {
        allSale = new ArrayList<>();
    }

    /**
     * Creates an instance if there is none or return the current one
     * @return: an instance of this class
     */
    public static SaleLog getInstance()
    {
        if (instance == null)
        {
            instance = new SaleLog();
        }
        return instance;
    }

    /**
     * Adds a sale to the list of all sales.
     * @param saleInfo The SaleDTO containing information about the sale to be added.
     */
    public void addSale(SaleDTO saleInfo)
    {
        allSale.add(saleInfo);
    }
    /**
     * Retrieves a list of all sales.
     * @return List of SaleDTO containing information about all sales.
     */
    public List<SaleDTO> getAllSale()
    {
        return this.allSale;
    }


}
