package se.kth.iv1350.daniel.model.dto;

public record ItemDescriptionDTO(String name, String origin, String expirationDate)
{
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Description: ");
        sb.append("name: ").append(name).append('\n');
        sb.append("origin: ").append(origin).append('\n');
        sb.append("expirationDate: ").append(expirationDate).append('\n');
        return sb.toString();
    }
}
