package se.kth.iv1350.daniel.model.dto;

import java.util.List;

public record ReceiptDTO(SaleDTO saleInfo, double amountPaid, double changeAmount)
{
    public List<ItemDTO> getShopList()
    {
        return this.saleInfo.shoplist();
    }
}
