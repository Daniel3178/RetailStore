package se.kth.iv1350.daniel.model;

import se.kth.iv1350.daniel.model.dto.DiscountDTO;

public class Discount
{
    DiscountDTO myDiscountDto;
    double myReducedAmount;
    public Discount(DiscountDTO discountDTO)
    {
        this.myDiscountDto = discountDTO;
        this.myReducedAmount = 0;
    }
    public void setReducedAmount(double amount)
    {
        this.myReducedAmount = amount;
    }
    public double getReducedAmount()
    {
        return this.myReducedAmount;
    }

    public DiscountDTO getDiscountDto()
    {
        return this.myDiscountDto;
    }
}
