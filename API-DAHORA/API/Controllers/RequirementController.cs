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
    public class RequirementController : ControllerBase
    {
        private readonly RequirementContext _contextRequeriments;
        private readonly CoordinatorContext _contextCoordinator;
        private readonly CourseContext _contextCourse;
        private readonly StudentContext _contextStudents;

        public RequirementController(RequirementContext contextRequeriments, CoordinatorContext coordinatorContext, CourseContext courseContext, StudentContext studentContext)
        {
            _contextRequeriments = contextRequeriments;
            _contextCoordinator = coordinatorContext;
            _contextCourse = courseContext;
            _contextStudents = studentContext;
        }


        // GET: api/Requirement
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Requirement>>> GetRequirement()
        {
            return await _contextRequeriments.Requirement.ToListAsync();
        }

        // GET: api/Requirement/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Requirement>> GetRequirement(long id)
        {
            var requirement = await _contextRequeriments.Requirement.FindAsync(id);

            if (requirement == null)
            {
                return NotFound();
            }

            return requirement;
        }

        // PUT: api/Requirement/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public async Task<IActionResult> PutRequirement(long id, Requirement requirement)
        {
            if (id != requirement.Id)
            {
                return BadRequest();
            }

            _contextRequeriments.Entry(requirement).State = EntityState.Modified;

            try
            {
                await _contextRequeriments.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!RequirementExists(id))
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

        // POST: api/Requirement
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<ActionResult<Requirement>> PostRequirement(Requirement requirement)
        {
            _contextRequeriments.Requirement.Add(requirement);
            await _contextRequeriments.SaveChangesAsync();

            return CreatedAtAction("GetRequirement", new { id = requirement.Id }, requirement);
        }

        // DELETE: api/Requirement/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteRequirement(long id)
        {
            var requirement = await _contextRequeriments.Requirement.FindAsync(id);
            if (requirement == null)
            {
                return NotFound();
            }

            _contextRequeriments.Requirement.Remove(requirement);
            await _contextRequeriments.SaveChangesAsync();

            return NoContent();
        }




        private bool RequirementExists(long id)
        {
            return _contextRequeriments.Requirement.Any(e => e.Id == id);
        }
    }
}
