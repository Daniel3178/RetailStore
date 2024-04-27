package se.kth.iv1350.daniel.integration.accounting_system;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountingSystemTest
{

    private AccountingSystem instanceToTest;
    @BeforeEach
    void setUp()
    {
        instanceToTest = new AccountingSystem();
    }

    @AfterEach
    void tearDown()
    {
        instanceToTest = null;
    }

    @Test
    void updateAccountingSystemNullArgument()
    {
        assertDoesNotThrow(()->instanceToTest.updateAccountingSystem(null), "It does not handle null argument exception");
    }
}