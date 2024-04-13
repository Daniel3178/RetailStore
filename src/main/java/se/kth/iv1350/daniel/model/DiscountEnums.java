package se.kth.iv1350.daniel.model;

public class DiscountEnums
{
    public enum DiscountAmountType
    {
        PRECENT,
        AMOUNT
    }

    public enum DiscountType
    {
        GOLDEN_SHOP(DiscountAmountType.PRECENT),
        SILVER_SHOP(DiscountAmountType.PRECENT),
        BRONZE_SHOP(DiscountAmountType.PRECENT),
        MEMBER_BONUS(DiscountAmountType.PRECENT),
        ITEM_DISC(DiscountAmountType.AMOUNT);

        private final DiscountAmountType amountType;

        DiscountType(DiscountAmountType amountType)
        {
            this.amountType = amountType;
        }

        public DiscountAmountType getAmountType()
        {
            return amountType;
        }
    }
}
