package se.kth.iv1350.daniel.model;

import se.kth.iv1350.daniel.model.dto.AppliedDiscountDTO;
import se.kth.iv1350.daniel.model.dto.DiscountDTO;

public class AmountDiscountImpl implements Discount
{

    @Override
    public AppliedDiscountDTO applyDiscount(double totalPrice , DiscountDTO discount)
    {
        double updatedTotalPrice =  totalPrice - discount.value();
        return new AppliedDiscountDTO(discount, discount.value(),
                                      updatedTotalPrice
        );
    }
}
