package se.kth.iv1350.daniel.model.dto;
import se.kth.iv1350.daniel.model.Item;

public record ReceiptDTO(SaleDTO saleInfo, double amountPaid, double changeAmount)
{
    public String generateReceipt()
    {
        StringBuilder sb = new StringBuilder();
        String formattedString;
        sb.append("\t                  RECEIPT                    \n");
        sb.append("\tSale ID: ").append(saleInfo.saleId()).append("\n");
        sb.append("\tDate: ").append(saleInfo.date()).append("\n");
        sb.append("\t---------------------------------------------\n");

        for (ItemDTO item : saleInfo.shoplist())
        {
            formattedString = String.format("%.2f", item.getItemPriceInclVat());
            sb.append(String.format("\t%-33s %s x %s\n", item.descDTO().name(), item.quantity(),
                                    formattedString));
        }
        sb.append("\n");
        for (AppliedDiscountDTO discount : saleInfo.discountsApplied())
        {
            formattedString = String.format("%.2f", discount.reducedAmount());
            sb.append(String.format("\tDiscount: \t\t%-20s -%s\n", discount.discountDTO().getDiscountType(),
                                    formattedString));
        }
        formattedString = String.format("%.2f", saleInfo.totalPrice());
        sb.append(String.format("\tTotal price: \t%28s\n", formattedString));
        formattedString = String.format("%.2f", saleInfo.totalVat());
        sb.append(String.format("\tTotal vat: \t%31s\n", formattedString));
        formattedString = String.format("%.2f", changeAmount);
        sb.append(String.format("\n\tPaid amount: \t%28s", amountPaid));
        sb.append(String.format("\n\tChange amount: \t%28s\n", formattedString));
        sb.append("\t---------------------------------------------\n");

        return sb.toString();
    }
}
