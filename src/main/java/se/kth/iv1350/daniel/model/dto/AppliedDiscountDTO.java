package se.kth.iv1350.daniel.model.dto;
import se.kth.iv1350.daniel.model.DiscountEnums.DiscountAmountType;

public record AppliedDiscountDTO(DiscountDTO discountDTO, double reducedAmount, double updatedTotalPrice)
{
}
