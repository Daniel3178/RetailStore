package se.kth.iv1350.daniel.model;

import se.kth.iv1350.daniel.model.dto.DiscountType;

public class DiscountFactory
{
    private static DiscountFactory instance;
    private final AmountDiscountImpl amountDiscount;
    private final PercentDiscountImpl percentDiscount;

    private DiscountFactory()
    {
        amountDiscount = new AmountDiscountImpl();
        percentDiscount = new PercentDiscountImpl();
    }

    public static DiscountFactory getInstance()
    {
        if (instance == null)
        {
            instance = new DiscountFactory();
        }
        return instance;
    }

    public Discount getDiscount(DiscountType type)
    {
        return switch (type)
        {
            case PERCENT -> percentDiscount;
            case AMOUNT -> amountDiscount;
        };
    }
}

