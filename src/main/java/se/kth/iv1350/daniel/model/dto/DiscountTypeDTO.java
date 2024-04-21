package se.kth.iv1350.daniel.model.dto;

import se.kth.iv1350.daniel.model.DiscountEnums;

public record DiscountTypeDTO(DiscountEnums.DiscountType discountType, double value)
{

}
