package se.kth.iv1350.daniel.model.dto;

import se.kth.iv1350.daniel.model.Sale;

public enum DiscountType
{
GOLDEN_SHOP(Sale.discountAmountType.PRECENT),
    SILVER_SHOP(Sale.discountAmountType.PRECENT),
    BRONZE_SHOP(Sale.discountAmountType.PRECENT),
    MEMBER_BONUS(Sale.discountAmountType.PRECENT),
    ITEM_DISC(Sale.discountAmountType.AMOUNT);

private final Sale.discountAmountType amountType;

    DiscountType(Sale.discountAmountType amountType)
    {
        this.amountType = amountType;
    }

    public Sale.discountAmountType getAmountType()
    {
        return amountType;
    }
}
