package se.kth.iv1350.daniel.model.dto;

public record LastSaleUpdateDTO(ItemDTO itemDTO, int quantity, double totalPrice, double totalVat)
{
}
