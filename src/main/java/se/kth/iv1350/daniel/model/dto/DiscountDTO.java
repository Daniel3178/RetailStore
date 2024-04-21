package se.kth.iv1350.daniel.model.dto;

import se.kth.iv1350.daniel.model.DiscountEnums;

public record DiscountDTO(DiscountTypeDTO discountTypeDTO, String discountDescription)
{
    public DiscountEnums.DiscountAmountType getAmountType()
    {
        return discountTypeDTO.discountType().getAmountType();
    }
    public DiscountEnums.DiscountType getDiscountType()
    {
        return discountTypeDTO.discountType();
    }
    public double getDiscountValue()
    {
        return discountTypeDTO.value();
    }
}
