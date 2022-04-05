namespace API_DaHora.Models;

public class Course
{
    public long Id { get; set; }
    public string? Name { get; set; }
    public double? AdditionalHoursTarget { get; set; }
    public Coordinator? Coordinator { get; set; }
}
