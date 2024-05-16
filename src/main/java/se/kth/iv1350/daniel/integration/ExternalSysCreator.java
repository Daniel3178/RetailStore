package se.kth.iv1350.daniel.integration;

import se.kth.iv1350.daniel.integration.accounting_system.AccountingSystem;
import se.kth.iv1350.daniel.integration.discount_db.DiscountDB;
import se.kth.iv1350.daniel.integration.inventory_db.InventoryDAO;

public class ExternalSysCreator
{
    AccountingSystem accountingSystem;
    DiscountDB discountDB;
    InventoryDAO inventoryDAO;
    public ExternalSysCreator()
    {
        this.accountingSystem = new AccountingSystem();
        this.discountDB = new DiscountDB();
        this.inventoryDAO = new InventoryDAO();
    }

    public AccountingSystem getAccountingSystem()
    {
        return this.accountingSystem;
    }
    public DiscountDB getDiscountDB()
    {
        return this.discountDB;
    }
    public InventoryDAO getInventory()
    {
        return this.inventoryDAO;
    }
}
