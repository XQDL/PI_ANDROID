using Microsoft.EntityFrameworkCore;
using System.Diagnostics.CodeAnalysis;

namespace API_DaHora.Models
{
    public class StudentContext : DbContext
    {
        public StudentContext(DbContextOptions<StudentContext> options)
            : base(options)
        {

        }

        public DbSet<Student> student { get; set; } = null!;

        protected override void OnModelCreating(ModelBuilder builder)
        {
               builder.Entity<Student>().HasKey(m => m.Id);
               base.OnModelCreating(builder);
        }


    }
}