package se.kth.iv1350.daniel.model;

import se.kth.iv1350.daniel.model.dto.AppliedDiscountDTO;
import se.kth.iv1350.daniel.model.dto.DiscountDTO;

public interface IDiscount
{
    AppliedDiscountDTO applyDiscount(double totalPrice, DiscountDTO discount);
}
