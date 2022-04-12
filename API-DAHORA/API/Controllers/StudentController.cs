#nullable disable
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using API_DaHora.Models;



namespace API_DaHora.Controllers;

[ApiController]
[Route("api/[controller]")]
public class UserController : ControllerBase
{
    private readonly StudentContext _context;

    public UserController(StudentContext context)
    {
        _context = context;
    }

            // GET: api/Users
    [HttpGet]
    public async Task<ActionResult<IEnumerable<Student>>> GetUser()
    {
        return await _context.student.ToListAsync();
    }

    // GET: User/5
    
    [HttpGet("{id}")]
    public async Task<ActionResult<Student>> GetUser(long id)
    {
        var Student = await _context.student.FindAsync(id);

        if (Student == null)
        {
            return NotFound();
        }

        return Student;
    }

    // PUT: api/Users/5
    // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
    [HttpPut("{id}")]
    public async Task<IActionResult> PutUser(long id, Student student)
    {
        if (id != student.Id)
        {
            return BadRequest();
        }

        _context.Entry(student).State = EntityState.Modified;

        try
        {
            await _context.SaveChangesAsync();
        }
        catch (DbUpdateConcurrencyException)
        {
            if (!StudentExists(id))
            {
                return NotFound();
            }
            else
            {
                throw;
            }
        }

        return NoContent();
    }

    // POST: api/Users
    // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
    [HttpPost]
    public async Task<ActionResult<Student>> PostStudent(Student student)
    {
        _context.student.Add(student);
        await _context.SaveChangesAsync();

        return CreatedAtAction(nameof(GetUser), new { id = student.Id }, student);
    }

    // DELETE: api/Users/5
    [HttpDelete("{id}")]
    public async Task<IActionResult> DeleteStudent(long id)
    {
        var user = await _context.student.FindAsync(id);
        if (user == null)
        {
            return NotFound();
        }

        _context.student.Remove(user);
        await _context.SaveChangesAsync();

        return NoContent();
    }

    private bool StudentExists(long id)
    {
        return _context.student.Any(e => e.Id == id);
    }
}
