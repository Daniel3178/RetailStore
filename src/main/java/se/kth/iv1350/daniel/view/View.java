package se.kth.iv1350.daniel.view;
import se.kth.iv1350.daniel.controller.Controller;
import se.kth.iv1350.daniel.model.dto.AppliedDiscountDTO;
import se.kth.iv1350.daniel.model.dto.LastSaleUpdateDTO;

import java.util.List;

public class View
{
    private final Controller ctr;
    public View(Controller ctr)
    {
        this.ctr = ctr;
    }

    public void runSampleTest(double customerPayAmount)
    {
        ctr.startNewSale();
        for (int i = 101; i< 110; i++)
        {
            LastSaleUpdateDTO lastSaleUpdate = ctr.addItem(i, i-100);
            System.out.println(lastSaleUpdate.toString());
        }

        List<AppliedDiscountDTO> appliedDiscounts = ctr.applyDiscountsOnSale();
        for(AppliedDiscountDTO ad : appliedDiscounts)
        {
            System.out.println(ad.toString());
        }
        AppliedDiscountDTO appliedDiscountByCustomerId = ctr.applyDiscountByCustomerId(1);
        System.out.println(appliedDiscountByCustomerId.toString());
        System.out.printf("[*]\tCashier should return: %.2f SEK\n", ctr.pay(customerPayAmount));
        ctr.endSale();
    }

}
