using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace API_DaHora.Migrations
{
    public partial class ADD_BLOB : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<byte[]>(
                name: "Attachment",
                table: "Requirement",
                type: "longblob",
                nullable: true);

            migrationBuilder.AddColumn<string>(
                name: "Type",
                table: "Requirement",
                type: "longtext",
                nullable: true)
                .Annotation("MySql:CharSet", "utf8mb4");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "Attachment",
                table: "Requirement");

            migrationBuilder.DropColumn(
                name: "Type",
                table: "Requirement");
        }
    }
}
