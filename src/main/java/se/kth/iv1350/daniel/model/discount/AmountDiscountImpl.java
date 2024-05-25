package se.kth.iv1350.daniel.model.discount;

import se.kth.iv1350.daniel.model.dto.AppliedDiscountDTO;
import se.kth.iv1350.daniel.model.dto.DiscountDTO;

public class AmountDiscountImpl implements Discount
{
    AmountDiscountImpl()
    {

    }

    /**
     * Applies a discount to the total price and returns the result.
     *
     * @param totalPrice the original total price
     * @param discount the discount to apply
     * @return an AppliedDiscountDTO with the discount, the amount reduced, and the new total price
     */
    @Override
    public AppliedDiscountDTO applyDiscount(double totalPrice , DiscountDTO discount)
    {
        double updatedTotalPrice =  totalPrice - discount.value();
        return new AppliedDiscountDTO(discount, discount.value(),
                                      updatedTotalPrice
        );
    }
}
