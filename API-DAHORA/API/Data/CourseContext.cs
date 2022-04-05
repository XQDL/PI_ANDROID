#nullable disable
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using API_DaHora.Models;

    public class CourseContext : DbContext
    {
        public CourseContext (DbContextOptions<CourseContext> options)
            : base(options)
        {
        }

        public DbSet<API_DaHora.Models.Course> Course { get; set; }
    }
