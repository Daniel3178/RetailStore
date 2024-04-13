package se.kth.iv1350.daniel.model.dto;

import se.kth.iv1350.daniel.model.Discount;
import se.kth.iv1350.daniel.model.Item;

import java.util.List;

public record SaleDTO(List<Item> shoplist, double totalPrice,double totalVat, String date, List<AppliedDiscountDTO> discountsApplied)
{
}
