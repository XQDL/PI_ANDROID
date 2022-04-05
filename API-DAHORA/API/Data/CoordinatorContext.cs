#nullable disable
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using API_DaHora.Models;

    public class CoordinatorContext : DbContext
    {
        public CoordinatorContext (DbContextOptions<CoordinatorContext> options)
            : base(options)
        {
        }

        public DbSet<API_DaHora.Models.Coordinator> Coordinator { get; set; }
    }
