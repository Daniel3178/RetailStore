package se.kth.iv1350.daniel.integration.accounting_system;

import se.kth.iv1350.daniel.model.dto.SaleDTO;

public class AccountingSystem
{
    private static AccountingSystem instance;
    private AccountingSystem()
    {

    }
    public static AccountingSystem getInstance()
    {
        if (instance == null)
        {
            instance = new AccountingSystem();
        }
        return instance;
    }
    /**
     * Updates the accounting system with information about a sale.
     *
     * @param saleInfo The SaleDTO containing information about the sale.
     */
    public void updateAccountingSystem(SaleDTO saleInfo)
    {
        System.out.println("[!]\tAccounting System is updated");
    }
}
