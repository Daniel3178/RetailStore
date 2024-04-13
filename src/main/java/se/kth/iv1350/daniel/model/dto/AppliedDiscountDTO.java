package se.kth.iv1350.daniel.model.dto;

public record AppliedDiscountDTO(DiscountDTO discountDTO, double reducedAmount, double updatedTotalPrice)
{
}
