package se.kth.iv1350.daniel.model;

import se.kth.iv1350.daniel.model.dto.AppliedDiscountDTO;
import se.kth.iv1350.daniel.model.dto.DiscountDTO;

public class Discount
{
    private final DiscountDTO discountInfo;
    private final IDiscount discountBehaviour;

    public Discount(DiscountDTO discountInfo, IDiscount disBehaviour)
    {
        this.discountInfo = discountInfo;
        discountBehaviour = disBehaviour;
    }

    /**
     * Task: It applies a discount to the total price
     *
     * @param totalPrice: total price on which the discount will be applied
     * @return : An object that tells what kind of discount, the reduced amount from total price and the updated total
     * price after the discount has been applied
     */
    public AppliedDiscountDTO applyDiscount(double totalPrice)
    {
        return discountBehaviour.applyDiscount(totalPrice, discountInfo);
    }

}
