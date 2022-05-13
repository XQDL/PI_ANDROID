namespace API_DaHora.Models;


public class Requirement
{
    public long Id { get; set; }
    public string? Tittle { get; set; }
    public DateTime? StartDate { get; set; }
    public DateTime? EndDate { get; set; }
    public double? WorkLoad { get; set; }
    public string? Comments { get; set; }
    public string? AttachmentAdress { get; set; }
    public string? InstitutionName { get; set; }
    public string? Type { get; set; }
    public DateTime? CreatedTime { get; set; }
    public DateTime? ApprovedTime { get; set; }
    public string? Reason { get; set; }
    public byte[]? Attachment { get; set; }
    public long? StudentId { get; set; }

}
