using Microsoft.EntityFrameworkCore;
using API_DaHora.Models;
using Microsoft.Extensions.DependencyInjection;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddControllers();

// Database connection Config
var connection = builder.Configuration["ConexaoSqlite:SqliteConnectionString"];

builder.Services.AddDbContext<StudentContext>(options => options.UseSqlite(connection));

builder.Services.AddDbContext<CoordinatorContext>(options => options.UseSqlite(connection));

builder.Services.AddDbContext<CourseContext>(options => options.UseSqlite(connection));

builder.Services.AddDbContext<RequirementContext>(options => options.UseSqlite(connection));



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
