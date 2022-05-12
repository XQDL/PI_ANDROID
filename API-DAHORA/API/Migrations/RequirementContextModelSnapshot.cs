﻿// <auto-generated />
using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;

#nullable disable

namespace API_DaHora.Migrations
{
    [DbContext(typeof(RequirementContext))]
    partial class RequirementContextModelSnapshot : ModelSnapshot
    {
        protected override void BuildModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .HasAnnotation("ProductVersion", "6.0.4")
                .HasAnnotation("Relational:MaxIdentifierLength", 64);

            modelBuilder.Entity("API_DaHora.Models.Requirement", b =>
                {
                    b.Property<long>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("bigint");

                    b.Property<DateTime?>("ApprovedTime")
                        .HasColumnType("datetime(6)");

                    b.Property<string>("AttachmentAdress")
                        .HasColumnType("longtext");

                    b.Property<string>("Comments")
                        .HasColumnType("longtext");

                    b.Property<DateTime?>("CreatedTime")
                        .HasColumnType("datetime(6)");

                    b.Property<DateTime?>("EndDate")
                        .HasColumnType("datetime(6)");

                    b.Property<string>("InstitutionName")
                        .HasColumnType("longtext");

                    b.Property<string>("Reason")
                        .HasColumnType("longtext");

                    b.Property<DateTime?>("StartDate")
                        .HasColumnType("datetime(6)");

                    b.Property<long?>("StudentId")
                        .HasColumnType("bigint");

                    b.Property<string>("Tittle")
                        .HasColumnType("longtext");

                    b.Property<double?>("WorkLoad")
                        .HasColumnType("double");

                    b.HasKey("Id");

                    b.ToTable("Requirement");
                });
#pragma warning restore 612, 618
        }
    }
}
