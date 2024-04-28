package se.kth.iv1350.daniel.integration.inventory_db;

enum ItemConstants
{
    QUANTITY(0),
    PRICE(1),
    VAT_RATE(2),
    NAME(3),
    DESCRIPTION(4),
    EXPIRATION_DATE(5),
    CATEGORY(6),
    SUPPLIER(7);

    private final int index;

    ItemConstants(int index){
        this.index = index;
    }
    int getIndex()
    {
        return this.index;
    }
}
