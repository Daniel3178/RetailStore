package se.kth.iv1350.daniel.integration.discount_db;

import se.kth.iv1350.daniel.model.Item;
import se.kth.iv1350.daniel.model.dto.DiscountDTO;
import se.kth.iv1350.daniel.model.dto.DiscountDescriptionDTO;
import se.kth.iv1350.daniel.model.dto.DiscountType;
import se.kth.iv1350.daniel.model.dto.DiscountTypeDTO;

import java.util.List;

public class DiscountDB
{
public DiscountDB()
{

}
    public DiscountDTO calculateReducedAmount(List<Item> allItem)
    {
        return new DiscountDTO(new DiscountTypeDTO(DiscountType.ITEM_DISC, 30.0),
                               new DiscountDescriptionDTO("This discount is based on shop list"));
    }

    public DiscountDTO findDiscountByCustomerId(int customerId)
    {
        return new DiscountDTO(new DiscountTypeDTO(DiscountType.MEMBER_BONUS, 0.05),
                               new DiscountDescriptionDTO("5% discount for member customer"));
    }

    public DiscountDTO findDiscountByTotalSum(double totalSum)
    {
        return new DiscountDTO(new DiscountTypeDTO(DiscountType.GOLDEN_SHOP, 0.10),
                               new DiscountDescriptionDTO("10% discount on shop over 100 sek"));

    }
}
