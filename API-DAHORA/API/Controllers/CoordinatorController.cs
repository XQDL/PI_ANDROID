#nullable disable
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using API_DaHora.Models;

namespace API_DaHora.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class CoordinatorController : ControllerBase
    {
        private readonly CoordinatorContext _context;

        public CoordinatorController(CoordinatorContext context)
        {
            _context = context;
        }

        // GET: api/Coordinator
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Coordinator>>> GetCoordinator()
        {
            return await _context.Coordinator.ToListAsync();
        }

        // GET: api/Coordinator/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Coordinator>> GetCoordinator(long id)
        {
            var coordinator = await _context.Coordinator.FindAsync(id);

            if (coordinator == null)
            {
                return NotFound();
            }

            return coordinator;
        }

        // PUT: api/Coordinator/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public async Task<IActionResult> PutCoordinator(long id, Coordinator coordinator)
        {
            if (id != coordinator.Id)
            {
                return BadRequest();
            }

            _context.Entry(coordinator).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!CoordinatorExists(id))
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

        // POST: api/Coordinator
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<ActionResult<Coordinator>> PostCoordinator(Coordinator coordinator)
        {
            _context.Coordinator.Add(coordinator);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetCoordinator", new { id = coordinator.Id }, coordinator);
        }

        // DELETE: api/Coordinator/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteCoordinator(long id)
        {
            var coordinator = await _context.Coordinator.FindAsync(id);
            if (coordinator == null)
            {
                return NotFound();
            }

            _context.Coordinator.Remove(coordinator);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool CoordinatorExists(long id)
        {
            return _context.Coordinator.Any(e => e.Id == id);
        }
    }
}
