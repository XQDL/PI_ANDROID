namespace API_DaHora.Models;

public class Student
{
    public long Id { get; set; }
    public string? Name { get; set; }
    public string? Email { get; set; }
    public string? Password { get; set; }
    public string? Register { get; set; }
    public double? AdditionalHoursPerformed { get; set; }
    public bool? HasCompletedHours { get; set; }
    public Course? Course { get; set; }
}
