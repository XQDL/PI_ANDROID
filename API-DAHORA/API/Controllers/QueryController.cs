#nullable disable
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using API_DaHora.Models;
using API_DaHora.Services;

namespace API_DaHora.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class QueryController : ControllerBase
    {
        private readonly RequirementContext _contextRequeriments;
        private readonly CoordinatorContext _contextCoordinator;
        private readonly CourseContext _contextCourse;
        private readonly StudentContext _contextStudents;
        private readonly QueryService _service;




        public QueryController(QueryService service, RequirementContext contextRequeriments, CoordinatorContext coordinatorContext, CourseContext courseContext, StudentContext studentContext){         
            _service = service;
            _contextRequeriments = contextRequeriments;
            _contextCoordinator = coordinatorContext;
            _contextCourse = courseContext;
            _contextStudents = studentContext;
        }




        // GET: api/Query/5
        [HttpGet("get_requirements_by_coordinator/{id}")]
        public async Task<ActionResult<Requirement>> GetRequirementByCoordinator(long id)
        {

            var requirements = await _service.GetRequirementByCoordinator(id);

            if (requirements == null)
            {
                return NotFound();
            }

            return Ok(requirements);
        }



        [HttpGet("get_requirements_by_coordinator_to_approve/{id}")]
        public async Task<ActionResult<Requirement>> GetRequirementsToApprove(long id)
        {
            var requirements = await _service.GetRequirementByCoordinator(id);

            requirements = requirements.Where(r => r.Type == "created").ToList();

            if (requirements == null)
            {
                return NotFound();
            }

            return Ok(requirements);
        }

        [HttpGet("get_requirements_by_student/{id}")]
        public async Task<ActionResult<Requirement>> GetRequirementsByStudent(long id)
        {
            var requirements = await _service.GetRequirementByStudent(id);



            if (requirements == null)
            {
                return NotFound();
            }

            return Ok(requirements);
        }

        [HttpGet("get_student_by_coordinator/{id}")]
        public async Task<ActionResult<Student>> GetStudentByCoordinator(long id)
        {
            var students = await _service.GetStudentByCoordinator(id);

            if (students == null)
            {
                return NotFound();
            }

            return Ok(students);
        }

        [HttpGet("get_student_by_course/{id}")]
        public async Task<ActionResult<Student>> GetStudentByCourse(long id)
        {
            var students = await _service.GetStudentByCourse(id);

            if (students == null)
            {
                return NotFound();
            }

            return Ok(students);
        }
 
        


    }
}
