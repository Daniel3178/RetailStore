package se.kth.iv1350.daniel.integration.discount_db;

import se.kth.iv1350.daniel.model.dto.DiscountDTO;
//import se.kth.iv1350.daniel.model.DiscountEnums.DiscountType;
//import se.kth.iv1350.daniel.model.dto.DiscountTypeDTO;
import se.kth.iv1350.daniel.model.dto.DiscountType;
import se.kth.iv1350.daniel.model.dto.ItemDTO;

import java.util.List;

public class DiscountDB
{
    private static DiscountDB instance;
    private DiscountDB()
    {
    }
    public static DiscountDB getInstance()
    {
        if (instance == null)
        {
            instance = new DiscountDB();
        }
        return instance;
    }

    /**
     * Finds discount based on the provided list of items. This discount is based on the items present in the shop
     * list.
     *
     * @param allItem The list of items for which discount is to be calculated.
     * @return DiscountDTO containing the discount information.
     */
    public DiscountDTO findDiscountByShopList(List<ItemDTO> allItem)
    {
        return new DiscountDTO(
                30.0, DiscountType.AMOUNT, "Item Discount",
                "This discount is based on shop list"
        );
    }

    /**
     * Finds discount based on the provided customer ID. 5% discount for member customers.
     *
     * @param customerId The ID of the customer for which discount is to be calculated.
     * @return DiscountDTO containing the discount information.
     */
    public DiscountDTO findDiscountByCustomerId(int customerId)
    {
        return new DiscountDTO(
                0.05, DiscountType.PERCENT, "Member Bonus",
                "5% discount for member customer"
        );
    }

    /**
     * Finds discount based on the total sum of the purchase. 10% discount on shop total over 100 sek.
     *
     * @param totalSum The total sum of the purchase for which discount is to be calculated.
     * @return DiscountDTO containing the discount information.
     */
    public DiscountDTO findDiscountByTotalSum(double totalSum)
    {
        return new DiscountDTO(
                0.10, DiscountType.PERCENT, "Golden Shop",
                "10% discount on shop over 100 sek"
        );

    }
}
