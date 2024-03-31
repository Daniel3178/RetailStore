package se.kth.iv1350.daniel.model;

public class Sale
{
    private static Sale currentSale = null;

    private Sale() {}

    /**
     * A part of Singletone design pattern
     *
     * @return: the current instance of this class.
     */
    public static Sale getInstance()
    {
        if (currentSale == null)
        {
            currentSale = new Sale();
        }
        return currentSale;
    }



}
