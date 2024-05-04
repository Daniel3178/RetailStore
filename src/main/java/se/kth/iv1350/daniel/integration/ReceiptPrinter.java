package se.kth.iv1350.daniel.integration;

import se.kth.iv1350.daniel.model.dto.AppliedDiscountDTO;
import se.kth.iv1350.daniel.model.dto.ItemDTO;
import se.kth.iv1350.daniel.model.dto.ReceiptDTO;

public class ReceiptPrinter
{
    public void printReceipt(ReceiptDTO receipt)
    {
        System.out.println("[!]\t++++++++++++ [PRINTING RECEIPT] ++++++++++++");
        System.out.println(formatReceiptPrint(receipt));
        System.out.println("[!]\t++++++++++++   [DONE PRINTING]   ++++++++++++");
    }

    private String formatReceiptPrint(ReceiptDTO receipt)
    {
        StringBuilder sb = new StringBuilder();
        String formattedString;
        sb.append("\t                  RECEIPT                    \n");
        sb.append("\tSale ID: ").append(receipt.saleInfo().saleId()).append("\n");
        sb.append("\tDate: ").append(receipt.saleInfo().date()).append("\n");
        sb.append("\t---------------------------------------------\n");
        sb.append(writeItem(receipt)).append("\n").append(writeAppliedDiscount(receipt));
        formattedString = String.format("%.2f", receipt.saleInfo().totalPrice());
        sb.append(String.format("\tTotal price: \t%28s\n", formattedString));
        formattedString = String.format("%.2f", receipt.saleInfo().totalVat());
        sb.append(String.format("\tTotal vat: \t%31s\n", formattedString));
        formattedString = String.format("%.2f", receipt.changeAmount());
        sb.append(String.format("\n\tPaid amount: \t%28s", receipt.amountPaid()));
        sb.append(String.format("\n\tChange amount: \t%28s\n", formattedString));
        sb.append("\t---------------------------------------------\n");
        return sb.toString();
    }

    private String writeAppliedDiscount(ReceiptDTO receipt)
    {
        StringBuilder sb = new StringBuilder();
        String formattedString;
        for (AppliedDiscountDTO discount : receipt.saleInfo().discountsApplied())
        {
            formattedString = String.format("%.2f", discount.reducedAmount());
            sb.append(String.format("\tDiscount: \t\t%-20s -%s\n", discount.discountDTO().getDiscountType(),
                                    formattedString));
        }
        return sb.toString();
    }

    private String writeItem(ReceiptDTO receipt)
    {
        StringBuilder sb = new StringBuilder();
        String formattedString;
        for (ItemDTO item : receipt.saleInfo().shoplist())
        {
            formattedString = String.format("%.2f", item.getItemPriceInclVat());
            sb.append(String.format("\t%-33s %s x %s\n", item.descDTO().name(), item.quantity(),
                                    formattedString));
        }
        return sb.toString();
    }

}
