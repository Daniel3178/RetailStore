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
        LastSaleUpdateDTO lastSaleUpdate = ctr.addItem(101, 5);
        System.out.println(lastSaleUpdate.toString());
        LastSaleUpdateDTO lastSaleUpdate2 = ctr.addItem(102, 2);
        System.out.println(lastSaleUpdate2.toString());
        List<AppliedDiscountDTO> appliedDiscounts = ctr.applyDiscountsOnSale();
        for(AppliedDiscountDTO ad : appliedDiscounts)
        {
            System.out.println(ad.toString());
        }
        AppliedDiscountDTO appliedDiscountByCustomerId = ctr.applyDiscountByCustomerId(1);
        System.out.println(appliedDiscountByCustomerId.toString());
        System.out.printf("Cashier should return: %.2f SEK\n", ctr.pay(customerPayAmount));
        ctr.endSale();
    }

}
