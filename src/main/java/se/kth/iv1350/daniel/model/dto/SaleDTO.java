package se.kth.iv1350.daniel.model.dto;

import se.kth.iv1350.daniel.model.Item;

import java.util.List;

public record SaleDTO(List<Item> itemList, double totalPrice, List<DiscountDTO> discountsApplied, double vatAmount, String date)
{
}
