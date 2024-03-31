package se.kth.iv1350.daniel.integration.discount_db;

import se.kth.iv1350.daniel.model.Item;

import java.util.List;

public class DiscountDB
{
    private static DiscountDB discountDB = null;
    private DiscountDB()
    {

    }
    public static DiscountDB getInstance()
    {
        if (discountDB == null)
        {
            discountDB = new DiscountDB();
        }
        return discountDB;
    }
    public double calculateReducedAmount(List<Item> allItem)
    {
        return 20.0;
    }
}
