package se.kth.iv1350.daniel.model.dto;

public record ItemDescriptionDTO(String name, String description, String expirationDate, String category, String supplier)
{
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("name: ").append(name).append('\n');
        sb.append("description: ").append(description).append('\n');
        sb.append("expirationDate: ").append(expirationDate).append('\n');
        sb.append("Category: ").append(category).append('\n');
        sb.append("Supplier: ").append(supplier).append('\n');
        return sb.toString();
    }
}
