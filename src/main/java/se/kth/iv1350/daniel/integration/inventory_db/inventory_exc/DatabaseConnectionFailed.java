package se.kth.iv1350.daniel.integration.inventory_db.inventory_exc;

public class DatabaseConnectionFailed extends Exception
{
    public DatabaseConnectionFailed()
    {
        super("Connection to inventory database failed.");
    }
}
