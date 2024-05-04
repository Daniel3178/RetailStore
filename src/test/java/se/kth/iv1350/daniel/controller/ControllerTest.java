package se.kth.iv1350.daniel.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.daniel.integration.ExternalSysCreator;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest
{
    private Controller instanceToTest;

    @BeforeEach
    void setUp()
    {
        ExternalSysCreator excSys = new ExternalSysCreator();
        instanceToTest = new Controller(excSys);

    }

    @AfterEach
    void tearDown()
    {
        instanceToTest = null;
    }

    @Test
    void startNewSale()
    {
        assertDoesNotThrow(()->instanceToTest, "There was a problem starting a new sale");
    }

    @Test
    void endSale()
    {
    }

    @Test
    void addItem()
    {
    }

    @Test
    void pay()
    {
    }
}