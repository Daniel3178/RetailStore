package se.kth.iv1350.daniel.model.dto;
import se.kth.iv1350.daniel.model.DiscountEnums.DiscountAmountType;

public record AppliedDiscountDTO(DiscountDTO discountDTO, double reducedAmount, double updatedTotalPrice)
{
    @Override
    public String toString()
    {
        String formattedDiscountValue = String.format("%.2f", discountDTO.getDiscountValue());
        String formattedDiscountValuePrecent = String.format("%.0f", discountDTO.getDiscountValue() * 100);
        String formattedReducedAmount = String.format("%.2f", reducedAmount);
        String formattedTotalPrice = String.format("%.2f", updatedTotalPrice);

        StringBuilder sb = new StringBuilder();
        sb.append("[*]\tDiscount Type : ").append(discountDTO.getDiscountType()).append(" ");
        if(this.discountDTO.getAmountType() == DiscountAmountType.PERCENT){
            sb.append(formattedDiscountValuePrecent).append("%\n");
        }
        else{
            sb.append(formattedDiscountValue).append(" SEK\n");
        }
        sb.append("[*]\tReduced amount : ").append(formattedReducedAmount).append(" SEK \n");
        sb.append("[*]\tTotal price after discount : ").append(formattedTotalPrice).append(" SEK \n");
        return sb.toString();
    }
}
