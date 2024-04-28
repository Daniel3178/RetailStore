package se.kth.iv1350.daniel.integration;
import se.kth.iv1350.daniel.model.dto.ReceiptDTO;

public class ReceiptPrinter
{
    public void printReceipt(ReceiptDTO receipt)
    {
        System.out.println("[!]\t++++++++++++ [PRINTING RECEIPT] ++++++++++++");
        System.out.println(receipt.generateReceipt());
        System.out.println("[!]\t++++++++++++   [DONE PRINTING]   ++++++++++++");
    }
}
