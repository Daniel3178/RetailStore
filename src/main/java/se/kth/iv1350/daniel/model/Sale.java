package se.kth.iv1350.daniel.model;

import se.kth.iv1350.daniel.model.dto.DiscountDTO;
import se.kth.iv1350.daniel.model.dto.DiscountRequestDTO;
import se.kth.iv1350.daniel.model.dto.ItemDTO;
import se.kth.iv1350.daniel.model.dto.SaleDTO;

import java.util.List;

public class Sale
{
    private Customer currentCustomer = null;
    private Double totalPrice = null;
    private String currentDate;
    private double totalVat;
    private List<DiscountDTO> discounts = null;
    private Payment currentPayment = null;

    public Sale()
    {
        currentCustomer = new Customer();
    }

    /**
     * A part of Singletone design pattern
     *
     * @return: the current instance of this class.
     */

    public boolean contains(int itemId)
    {
        return currentCustomer.contains(itemId);
    }

    public void setCurrentPayment(Payment newPayment)
    {
        this.currentPayment = newPayment;
    }
    public Payment getCurrentPayment()
    {
        return this.currentPayment;
    }
    public void updateQuantity(int itemId, Integer quantity)
    {
        this.currentCustomer.updateQuantity(itemId, quantity);
    }


    public SaleDTO getSaleInfo()
    {
        return new SaleDTO(currentCustomer.getShopList(), this.totalPrice, this.discounts, 20.0, "Today");
    }
    public void addItem(ItemDTO itemDTO, Integer quantity)
    {
        this.currentCustomer.addItem(itemDTO, quantity);
    }

    public void setCustomerId(Integer customerId)
    {
        currentCustomer.setCustomerId(customerId);
    }

    public List<Item> getCustomerShopList()
    {
        return currentCustomer.getShopList();
    }

    private void calculateTotalShopListSum()
    {
        double tempTotalPrice = 0;
        for(Item item : currentCustomer.getShopList())
        {
            tempTotalPrice += item.getItemInfo().getPriceAmount();
        }
        this.totalPrice = tempTotalPrice;
    }
    public void decreaseTotalPrice(double amount)
    {
        if (this.totalPrice == null)
        {
            calculateTotalShopListSum();
            this.totalPrice -= amount;
            return;
        }
        this.totalPrice -= amount;

    }

    public void applyDiscounts(List<DiscountDTO> discounts)
    {
        setCurrentDiscounts(discounts);
        for(DiscountDTO discount: discounts)
        {
            this.totalPrice -= discount.percentage() * this.totalPrice;
        }
    }
    public DiscountRequestDTO getDiscountInfo()
    {
        return new DiscountRequestDTO(currentCustomer.getCustomerId(), this.totalPrice);
    }

    private void setCurrentDiscounts(List<DiscountDTO> discounts)
    {
        this.discounts = discounts;
    }


}
