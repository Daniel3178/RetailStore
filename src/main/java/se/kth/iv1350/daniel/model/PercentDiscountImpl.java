package se.kth.iv1350.daniel.model;
import se.kth.iv1350.daniel.model.dto.AppliedDiscountDTO;
import se.kth.iv1350.daniel.model.dto.DiscountDTO;

public class PercentDiscountImpl implements Discount
{

    /**
     * Applies a discount to the total price and returns the result.
     *
     * @param totalPrice the original total price
     * @param discount the discount to apply
     * @return an AppliedDiscountDTO with the discount, the amount reduced, and the new total price
     */
    @Override
    public AppliedDiscountDTO applyDiscount(double totalPrice, DiscountDTO discount)
    {
        double tempReducedAmount = totalPrice * discount.value();
        double updatedTotalPrice = totalPrice - tempReducedAmount;
        return new AppliedDiscountDTO(discount, tempReducedAmount, updatedTotalPrice);
    }
}
