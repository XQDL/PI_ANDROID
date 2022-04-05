#nullable disable
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using API_DaHora.Models;

    public class RequirementContext : DbContext
    {
        public RequirementContext (DbContextOptions<RequirementContext> options)
            : base(options)
        {
        }

        public DbSet<API_DaHora.Models.Requirement> Requirement { get; set; }
    }
