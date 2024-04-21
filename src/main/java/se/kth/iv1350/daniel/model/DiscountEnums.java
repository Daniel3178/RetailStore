package se.kth.iv1350.daniel.model;

public class DiscountEnums
{
    /**
     * It is used to specify how the discount math should be implemented.
     * For instance an Amount tells that the value should be subtracted from
     * the total sum whereas precent tells the percentage that should be subtracted
     * from the total sum.
     */
    public enum DiscountAmountType
    {
        PERCENT,
        AMOUNT
    }

    /**
     * It specifies the type of discount and how it should be calculated
     */
    public enum DiscountType
    {
        GOLDEN_SHOP(DiscountAmountType.PERCENT),
        SILVER_SHOP(DiscountAmountType.PERCENT),
        BRONZE_SHOP(DiscountAmountType.PERCENT),
        MEMBER_BONUS(DiscountAmountType.PERCENT),
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
