package se.kth.iv1350.daniel.view;
import se.kth.iv1350.daniel.controller.Controller;
import se.kth.iv1350.daniel.model.DiscountEnums;
import se.kth.iv1350.daniel.model.dto.AppliedDiscountDTO;
import se.kth.iv1350.daniel.model.dto.ItemDTO;
import se.kth.iv1350.daniel.model.dto.ItemDescriptionDTO;
import se.kth.iv1350.daniel.model.dto.LastSaleUpdateDTO;

import java.util.List;
import java.util.Random;

public class View
{
    private final Controller ctr;
    public View(Controller ctr)
    {
        this.ctr = ctr;
    }

    public void runSampleTest(double customerPayAmount)
    {
        Random rand = new Random();
        ctr.startNewSale();
        for (int i = 101, j = rand.nextInt(1, 10) ; i< 110; i++, j=rand.nextInt(1, 10))
        {
            LastSaleUpdateDTO lastSaleUpdate = ctr.addItem(i, j);
            System.out.println(stringifyLastUpdateToCashier(lastSaleUpdate));
        }

        List<AppliedDiscountDTO> appliedDiscounts = ctr.applyDiscountsOnSale();
        for(AppliedDiscountDTO ad : appliedDiscounts)
        {
            System.out.println(stringifyAppliedDiscToCashier(ad));
        }
        AppliedDiscountDTO appliedDiscountByCustomerId = ctr.applyDiscountByCustomerId(1);
        System.out.println(stringifyAppliedDiscToCashier(appliedDiscountByCustomerId));
        System.out.printf("[*]\tCashier should return: %.2f SEK\n", ctr.pay(customerPayAmount));
        ctr.endSale();
    }

    private String stringifyAppliedDiscToCashier(AppliedDiscountDTO ad)
    {
        String formattedDiscountValue = String.format("%.2f", ad.discountDTO().getDiscountValue());
        String formattedDiscountValuePrecent = String.format("%.0f", ad.discountDTO().getDiscountValue() * 100);
        String formattedReducedAmount = String.format("%.2f", ad.reducedAmount());
        String formattedTotalPrice = String.format("%.2f", ad.updatedTotalPrice());

        StringBuilder sb = new StringBuilder();
        sb.append("[*]\tDiscount Type : ").append(ad.discountDTO().getDiscountType()).append(" ");
        if(ad.discountDTO().getAmountType() == DiscountEnums.DiscountAmountType.PERCENT){
            sb.append(formattedDiscountValuePrecent).append("%\n");
        }
        else{
            sb.append(formattedDiscountValue).append(" SEK\n");
        }
        sb.append("[*]\tReduced amount : ").append(formattedReducedAmount).append(" SEK \n");
        sb.append("[*]\tTotal price after discount : ").append(formattedTotalPrice).append(" SEK \n");
        return sb.toString();
    }

    private String stringifyLastUpdateToCashier(LastSaleUpdateDTO updateDTO)
    {
        String formattedTotalPrice = String.format("%.2f", updateDTO.totalPrice());
        String formattedTotalVat = String.format("%.2f", updateDTO.totalVat());

        StringBuilder sb = new StringBuilder();
        sb.append("[*]\tItem Information:\n").append(stringifyItemToCashier(updateDTO.itemDTO()));
        sb.append("[*]\tQuantity: ").append(updateDTO.quantity()).append("\n\n");
        sb.append("[*]\tTotal Price: ").append(formattedTotalPrice).append(" SEK\n");
        sb.append("[*]\tTotal VAT: ").append(formattedTotalVat).append(" SEK\n");
        return sb.toString();
    }

    private String stringifyItemToCashier(ItemDTO item)
    {
        String formatedPriceInclVat = String.format("%.2f", item.price() * (1 + item.vatRate()));
        StringBuilder sb = new StringBuilder();
        sb.append("[*]\tItemID: ").append(item.itemId()).append("\n");
        sb.append("[*]\tPrice inclusive VAT: ").append(formatedPriceInclVat).append("\n");
        sb.append("[*]\tVAT Rate: ").append(item.vatRate()*100).append(" %\n");
        sb.append(stringifyItemDescToCashier(item.descDTO()));
        return sb.toString();
    }

    private String stringifyItemDescToCashier(ItemDescriptionDTO desc)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("[*]\tname: ").append(desc.name()).append('\n');
        sb.append("[*]\tdescription: ").append(desc.description()).append('\n');
        sb.append("[*]\texpirationDate: ").append(desc.expirationDate()).append('\n');
        sb.append("[*]\tCategory: ").append(desc.category()).append('\n');
        sb.append("[*]\tSupplier: ").append(desc.supplier()).append('\n');
        return sb.toString();
    }





}
