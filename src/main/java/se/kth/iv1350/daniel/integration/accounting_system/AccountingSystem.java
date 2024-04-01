package se.kth.iv1350.daniel.integration.accounting_system;

import se.kth.iv1350.daniel.model.dto.SaleDTO;

public class AccountingSystem
{
    private static AccountingSystem accountingSystem = null;
    private AccountingSystem()
    {
    }

    public static AccountingSystem getInstance()
    {
        if(accountingSystem == null)
        {
            accountingSystem = new AccountingSystem();
        }
        return accountingSystem;
    }

    public void updateAccountingSystem(SaleDTO saleInfo)
    {
        System.out.println("Accounting updated");
    }
}
