package se.kth.iv1350.daniel.model.dto;

import java.util.List;
public record SaleDTO(int saleId,List<ItemDTO> shoplist, double totalPrice,double totalVat, String date, List<AppliedDiscountDTO> discountsApplied)
{
}
