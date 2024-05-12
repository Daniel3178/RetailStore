package se.kth.iv1350.daniel.model;

import se.kth.iv1350.daniel.model.dto.AppliedDiscountDTO;
import se.kth.iv1350.daniel.model.dto.DiscountDTO;

public class PercentDiscountImpl implements Discount
{
    @Override
    public AppliedDiscountDTO applyDiscount(double totalPrice, DiscountDTO discount)
    {
        double tempReducedAmount = totalPrice * discount.value();
        double updatedTotalPrice = totalPrice - tempReducedAmount;
        return new AppliedDiscountDTO(discount, tempReducedAmount, updatedTotalPrice);
    }
}
