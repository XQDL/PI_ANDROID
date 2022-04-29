using Microsoft.EntityFrameworkCore;
using API_DaHora.Models;
using Microsoft.Extensions.DependencyInjection;
using API_DaHora.Services;
using Pomelo.EntityFrameworkCore.MySql;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddControllers();

// Database connection Config
var connection = builder.Configuration["ConexaoSqlite:DefaultConnection"];


builder.Services.AddDbContext<StudentContext>(options => options.UseMySql(connection, Microsoft.EntityFrameworkCore.ServerVersion.Parse("8.0.25-mysql")));

builder.Services.AddDbContext<CoordinatorContext>(options => options.UseMySql(connection, Microsoft.EntityFrameworkCore.ServerVersion.Parse("8.0.25-mysql")));

builder.Services.AddDbContext<CourseContext>(options => options.UseMySql(connection, Microsoft.EntityFrameworkCore.ServerVersion.Parse("8.0.25-mysql")));

builder.Services.AddDbContext<RequirementContext>(options => options.UseMySql(connection, Microsoft.EntityFrameworkCore.ServerVersion.Parse("8.0.25-mysql")));

builder.Services.AddScoped<AuthenticatorService>();

// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseDeveloperExceptionPage();
    app.UseSwaggerUI();
}
app.UseSwagger();


app.UseHttpsRedirection();

app.UseAuthorization();

app.MapControllers();

app.Run();
