package se.kth.iv1350.daniel.model.dto;

import se.kth.iv1350.daniel.model.DiscountEnums;

public record AppliedDiscountDTO(DiscountDTO discountDTO, double reducedAmount, double updatedTotalPrice)
{
    @Override
    public String toString()
    {
        String formattedDiscountValue = String.format("%.2f", discountDTO.discountTypeDTO().value());
        String formattedDiscountValuePrecent = String.format("%.0f", discountDTO.discountTypeDTO().value() * 100);
        String formattedReducedAmount = String.format("%.2f", reducedAmount);

        StringBuilder sb = new StringBuilder();
        sb.append("Discount Type :\n").append(discountDTO.discountTypeDTO().discountType()).append("\n");
        if(this.discountDTO.discountTypeDTO().discountType().getAmountType() == DiscountEnums.DiscountAmountType.PRECENT){
            sb.append("% ").append(formattedDiscountValuePrecent).append("\n");
        }
        else{
            sb.append(formattedDiscountValue).append(" SEK\n");
        }
        sb.append("Reduced Amount : ").append(formattedReducedAmount).append(" SEK \n");
        return sb.toString();
    }
}
