package se.kth.iv1350.daniel.integration.discount_db;

import se.kth.iv1350.daniel.model.Item;
import se.kth.iv1350.daniel.model.dto.DiscountDTO;

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
        return 2.0;
    }

    public DiscountDTO findDiscountByCustId(int customerId)
    {
        System.out.println("Finding discount by cust id is called");
        return new DiscountDTO("VIP", 0.2);
    }

    public DiscountDTO findDiscountByTotalSum(double totalSum)
    {
        System.out.println("Finding discount by total sum is called");
        return new DiscountDTO("PremiumShop", 0.1);

    }
}
