package se.kth.iv1350.daniel.view;

import se.kth.iv1350.daniel.controller.Controller;
import se.kth.iv1350.daniel.model.dto.AppliedDiscountDTO;
import se.kth.iv1350.daniel.model.dto.LastSaleUpdateDTO;
import se.kth.iv1350.daniel.model.dto.ReceiptDTO;

import java.util.List;

public class View
{
    private final Controller ctr;
    public View(Controller ctr)
    {
        this.ctr = ctr;
    }

    public void runSampleTest()
    {
        ctr.startNewSale();
        LastSaleUpdateDTO lastSaleUpdate = ctr.addItem(1, 5);
        List<AppliedDiscountDTO> appliedDiscounts = ctr.applyDiscountsOnSale();
        AppliedDiscountDTO appliedDiscountByCustomerId = ctr.applyDiscountByCustomerId(1);
        //ctr.applyDiscount(1);
//        ReceiptDTO receiptDTO = ctr.pay(102.0);
//        receiptDTO.print();
        ctr.pay(100);
        ctr.endSale();
    }

}
