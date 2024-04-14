package se.kth.iv1350.daniel.model;

import se.kth.iv1350.daniel.model.dto.SaleDTO;

import java.util.ArrayList;
import java.util.List;

public class SaleLog
{
    private static SaleLog instance;
    private final List<SaleDTO> allSale;
    private SaleLog() {
        allSale = new ArrayList<>();
    }
    public static SaleLog getInstance() {
        if (instance == null) {
            instance = new SaleLog();
        }
        return instance;
    }

    public void addSale(SaleDTO saleInfo)
    {
        allSale.add(saleInfo);
    }
}
