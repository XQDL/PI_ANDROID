namespace API_DaHora.Services;
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


public class QueryService{

    private readonly RequirementContext _contextRequeriments;
    private readonly CoordinatorContext _contextCoordinator;
    private readonly CourseContext _contextCourse;
    private readonly StudentContext _contextStudents;
    private readonly QueryService _service;

    public QueryService(RequirementContext contextRequeriments, CoordinatorContext coordinatorContext, CourseContext courseContext, StudentContext studentContext){         
        _contextRequeriments = contextRequeriments;
        _contextCoordinator = coordinatorContext;
        _contextCourse = courseContext;
        _contextStudents = studentContext;
    }

    public async Task<List<Requirement>> GetRequirementByCoordinator(long id)
    {

        var courses = await _contextCourse.Course.Where(c => c.CoordinatorId == id).ToListAsync();

        List<long> coursesIds = courses.Select(c => c.Id).ToList();
        var students = await _contextStudents.student.Where(s => coursesIds.Contains((long) s.CourseId)).ToListAsync();
        
        List<long> studentsIds = students.Select(s => s.Id).ToList();
        var requirements  = await _contextRequeriments.Requirement.Where(r => studentsIds.Contains((long) r.StudentId)).ToListAsync();

        return requirements;
    }

    public async Task<List<Requirement>> GetRequirementByStudent(long id)
    {
        var requirements  = await _contextRequeriments.Requirement.Where(r => r.StudentId == id).ToListAsync();

        return requirements;
    }

    public async Task<List<Student>> GetStudentByCoordinator(long id)
    {
        var courses = await _contextCourse.Course.Where(c => c.CoordinatorId == id).ToListAsync();

        List<long> coursesIds = courses.Select(c => c.Id).ToList();
        var students = await _contextStudents.student.Where(s => coursesIds.Contains((long) s.CourseId)).ToListAsync();

        return students;
    }


    public async Task<List<Student>> GetStudentByCourse(long id)
    {
        var students = await _contextStudents.student.Where(s => s.CourseId == id).ToListAsync();

        return students;
    }

}