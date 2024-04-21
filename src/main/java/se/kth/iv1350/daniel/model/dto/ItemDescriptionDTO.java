package se.kth.iv1350.daniel.model.dto;

public record ItemDescriptionDTO(String name, String description, String expirationDate, String category, String supplier)
{
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("[*]\tname: ").append(name).append('\n');
        sb.append("[*]\tdescription: ").append(description).append('\n');
        sb.append("[*]\texpirationDate: ").append(expirationDate).append('\n');
        sb.append("[*]\tCategory: ").append(category).append('\n');
        sb.append("[*]\tSupplier: ").append(supplier).append('\n');
        return sb.toString();
    }
}
