package se.kth.iv1350.daniel.view;

import se.kth.iv1350.daniel.controller.Controller;
import se.kth.iv1350.daniel.model.dto.ReceiptDTO;

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
        ctr.addItem(1, 1);
        ctr.applyDiscount(1);
        ReceiptDTO receiptDTO = ctr.pay(102.0);
        receiptDTO.print();
        ctr.endSale();
    }

}
