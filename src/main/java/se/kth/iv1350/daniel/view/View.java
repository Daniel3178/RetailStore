package se.kth.iv1350.daniel.view;

import se.kth.iv1350.daniel.controller.Controller;
import se.kth.iv1350.daniel.controller.exceptions.ConnectionFailed;
import se.kth.iv1350.daniel.integration.inventory_db.inventory_exc.ItemDoesNotExist;
import se.kth.iv1350.daniel.model.dto.AppliedDiscountDTO;
import se.kth.iv1350.daniel.model.dto.ItemDTO;
import se.kth.iv1350.daniel.model.dto.ItemDescriptionDTO;
import se.kth.iv1350.daniel.model.dto.LastSaleUpdateDTO;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class View
{
    private final Controller ctr;
    private final ErrorMessageHandler errorHandler;

    public View(Controller ctr) throws IOException
    {
        this.ctr = ctr;
        this.ctr.addSaleObserver(new TotalRevenueView());
        this.ctr.addSaleObserver(new TotalRevenueFileOutput());
        this.errorHandler = new ErrorMessageHandler();
    }

    public void runSampleTest(double customerPayAmount)
    {
        try
        {

            Random rand = new Random();
            ctr.startNewSale();
            for (
                    int itemId = 101, quantity = rand.nextInt(1, 10); itemId < 105;
                    itemId++, quantity = rand.nextInt(1, 10)
            )
            {
                LastSaleUpdateDTO lastSaleUpdate = ctr.addItem(itemId, quantity);
                System.out.println(stringifyLastUpdateToCashier(lastSaleUpdate));
            }

            //TODO: Uncomment the below lines to see the exception handling
//            LastSaleUpdateDTO lastSaleUpdateThrowsDbException = ctr.addItem(113, 2);
//            LastSaleUpdateDTO lastSaleUpdateThrowsItemDoesNotExc = ctr.addItem(111, 2);

            List<AppliedDiscountDTO> appliedDiscounts = ctr.applyDiscountsOnSale();
            for (AppliedDiscountDTO ad : appliedDiscounts)
            {
                System.out.println(stringifyAppliedDiscToCashier(ad));
            }
            AppliedDiscountDTO appliedDiscountByCustomerId = ctr.applyDiscountByCustomerId(1);
            System.out.println(stringifyAppliedDiscToCashier(appliedDiscountByCustomerId));
            double change = ctr.pay(customerPayAmount);
            System.out.printf("[*]\tCashier should return: %.2f SEK\n", change);
            ctr.endSale();
        }
        catch (ItemDoesNotExist exc)
        {
            errorHandler.showErrorMsg("Item ID " + exc.getNonExistedItemId() + " not found.");
        }
        catch (ConnectionFailed conExc)
        {
            errorHandler.showErrorMsg(conExc.getMessage());
        }
    }

    private String stringifyAppliedDiscToCashier(AppliedDiscountDTO ad)
    {
        String formattedDiscountValue = String.format("%.2f", ad.discountDTO().value());
        String formattedDiscountValuePrecent = String.format("%.0f", ad.discountDTO().value() * 100);
        String formattedReducedAmount = String.format("%.2f", ad.reducedAmount());
        String formattedTotalPrice = String.format("%.2f", ad.updatedTotalPrice());

        StringBuilder sb = new StringBuilder();
        sb.append("[*]\tDiscount Type : ").append(ad.discountDTO().discountName()).append(" ");
        if (ad.discountDTO().value() < 1 && ad.discountDTO().value() > 0)
        {
            sb.append(formattedDiscountValuePrecent).append("%\n");
        }
        else
        {
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
        String formatedPriceInclVat = String.format("%.2f", item.getItemPriceInclVat());
        StringBuilder sb = new StringBuilder();
        sb.append("[*]\tItemID: ").append(item.itemId()).append("\n");
        sb.append("[*]\tPrice inclusive VAT: ").append(formatedPriceInclVat).append("\n");
        sb.append("[*]\tVAT Rate: ").append(item.vatRate() * 100).append(" %\n");
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
