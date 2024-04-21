package se.kth.iv1350.daniel.integration.discount_db;

import se.kth.iv1350.daniel.model.Item;
import se.kth.iv1350.daniel.model.dto.DiscountDTO;
import se.kth.iv1350.daniel.model.DiscountEnums.DiscountType;
import se.kth.iv1350.daniel.model.dto.DiscountTypeDTO;

import java.util.List;

public class DiscountDB
{
    public DiscountDTO calculateReducedAmount(List<Item> allItem)
    {
        return new DiscountDTO(
                new DiscountTypeDTO(DiscountType.ITEM_DISC, 30.0),
                "This discount is based on shop list"
        );
    }

    public DiscountDTO findDiscountByCustomerId(int customerId)
    {
        return new DiscountDTO(
                new DiscountTypeDTO(DiscountType.MEMBER_BONUS, 0.05),
                "5% discount for member customer"
        );
    }

    public DiscountDTO findDiscountByTotalSum(double totalSum)
    {
        return new DiscountDTO(
                new DiscountTypeDTO(DiscountType.GOLDEN_SHOP, 0.10),
                "10% discount on shop over 100 sek"
        );

    }
}
