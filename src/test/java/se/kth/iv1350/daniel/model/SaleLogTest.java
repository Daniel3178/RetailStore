package se.kth.iv1350.daniel.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.daniel.model.dto.AppliedDiscountDTO;
import se.kth.iv1350.daniel.model.dto.SaleDTO;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SaleLogTest
{
    SaleLog instanceToTest;

    @BeforeEach
    void setUp()
    {
        instanceToTest = SaleLog.getInstance();
    }

    @AfterEach
    void tearDown()
    {
        instanceToTest = null;
    }

    @Test
    void addSaleDTOInstanceToSaleLog()
    {
        List<Item> shopList = new ArrayList<>();
        List<AppliedDiscountDTO> appliedDiscounts= new ArrayList<>();
        SaleDTO saleInfo = new SaleDTO(1, shopList, 20.0, 5.0, "Today", appliedDiscounts );
        int sizeBeforeAdding = instanceToTest.getAllSale().size();
        instanceToTest.addSale(saleInfo);
        assertTrue(
                instanceToTest.getAllSale().size() == sizeBeforeAdding + 1,
                "AddSale method does not add a SaleDTO instance"
        );
    }

    @Disabled
    @Test
    void addNullToSaleLog()
    {
        int sizeBeforeAdding = instanceToTest.getAllSale().size();
        instanceToTest.addSale(null);
        assertTrue(
                instanceToTest.getAllSale().size() == sizeBeforeAdding,
                "AddSale method does not handle adding null to the list"
        );
    }
}