package se.kth.iv1350.daniel.model;

import se.kth.iv1350.daniel.model.dto.AppliedDiscountDTO;
import se.kth.iv1350.daniel.model.dto.DiscountDTO;

public class Discount
{
    private final DiscountDTO discountInfo;

    public Discount(DiscountDTO discountInfo)
    {
        this.discountInfo = discountInfo;
    }

    /**
     * Task: It applies a discount to the total price
     * @param totalPrice: total price on which the discount will be applied
     * @return : An object that tells what kind of discount, the reduced amount from total price and the updated total
     * price after the discount has been applied
     */
    public AppliedDiscountDTO applyDiscount(double totalPrice)
    {
        return switch (discountInfo.getAmountType())
        {
            case AMOUNT -> handleApplyDiscountAmount(totalPrice);
            case PERCENT -> handleApplyDiscountPrecent(totalPrice);
        };
    }
    private AppliedDiscountDTO handleApplyDiscountAmount(double totalPrice)
    {
        double updatedTotalPrice =  totalPrice - discountInfo.getDiscountValue();
        return new AppliedDiscountDTO(discountInfo, discountInfo.getDiscountValue(),
                                      updatedTotalPrice
        );
    }

    private AppliedDiscountDTO handleApplyDiscountPrecent(double totalPrice)
    {
        double tempReducedAmount = totalPrice * discountInfo.getDiscountValue();
        double updatedTotalPrice = totalPrice - tempReducedAmount;
        return new AppliedDiscountDTO(discountInfo, tempReducedAmount, updatedTotalPrice);
    }

}
