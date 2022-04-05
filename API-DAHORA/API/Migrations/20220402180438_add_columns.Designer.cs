﻿// <auto-generated />
using System;
using API_DaHora.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Migrations;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;

#nullable disable

namespace API_DaHora.Migrations
{
    [DbContext(typeof(StudentContext))]
    [Migration("20220402180438_add_columns")]
    partial class add_columns
    {
        protected override void BuildTargetModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder.HasAnnotation("ProductVersion", "6.0.1");

            modelBuilder.Entity("API_DaHora.Models.Coordinator", b =>
                {
                    b.Property<long>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("INTEGER");

                    b.Property<string>("Email")
                        .HasColumnType("TEXT");

                    b.Property<string>("Name")
                        .HasColumnType("TEXT");

                    b.Property<string>("Password")
                        .HasColumnType("TEXT");

                    b.Property<string>("PhoneNumber")
                        .HasColumnType("TEXT");

                    b.HasKey("Id");

                    b.ToTable("Coordinator");
                });

            modelBuilder.Entity("API_DaHora.Models.Course", b =>
                {
                    b.Property<long>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("INTEGER");

                    b.Property<double?>("AdditionalHoursTarget")
                        .HasColumnType("REAL");

                    b.Property<long?>("CoordinatorId")
                        .HasColumnType("INTEGER");

                    b.Property<string>("Name")
                        .HasColumnType("TEXT");

                    b.HasKey("Id");

                    b.HasIndex("CoordinatorId");

                    b.ToTable("Course");
                });

            modelBuilder.Entity("API_DaHora.Models.Student", b =>
                {
                    b.Property<long>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("INTEGER");

                    b.Property<double?>("AdditionalHoursPerformed")
                        .HasColumnType("REAL");

                    b.Property<long?>("CourseId")
                        .HasColumnType("INTEGER");

                    b.Property<string>("Email")
                        .HasColumnType("TEXT");

                    b.Property<bool?>("HasCompletedHours")
                        .HasColumnType("INTEGER");

                    b.Property<string>("Name")
                        .HasColumnType("TEXT");

                    b.Property<string>("Password")
                        .HasColumnType("TEXT");

                    b.Property<string>("Register")
                        .HasColumnType("TEXT");

                    b.HasKey("Id");

                    b.HasIndex("CourseId");

                    b.ToTable("student");
                });

            modelBuilder.Entity("API_DaHora.Models.Course", b =>
                {
                    b.HasOne("API_DaHora.Models.Coordinator", "Coordinator")
                        .WithMany()
                        .HasForeignKey("CoordinatorId");

                    b.Navigation("Coordinator");
                });

            modelBuilder.Entity("API_DaHora.Models.Student", b =>
                {
                    b.HasOne("API_DaHora.Models.Course", "Course")
                        .WithMany()
                        .HasForeignKey("CourseId");

                    b.Navigation("Course");
                });
#pragma warning restore 612, 618
        }
    }
}
