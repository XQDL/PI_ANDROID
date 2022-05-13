using API_DaHora.Models.DTO;
using API_DaHora.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace API_DaHora.Services;
public class AuthenticatorService{


    private readonly CoordinatorContext _coordinatorContext;
    private readonly StudentContext _studentContext;



    public AuthenticatorService(CoordinatorContext coordinatorContext, StudentContext studentContext){
        _coordinatorContext = coordinatorContext;
        _studentContext = studentContext;
    }

    public IUser? Login(LoginDTO dto){

        Student? student = LoginStudent(dto);
        Coordinator? coordinator = LoginCoordinator(dto);

        if(student != null){
            return student;
        } 
        if(coordinator != null){
            return coordinator;
        }

        return null;
    }

    public Student? LoginStudent(LoginDTO dto){
        Student? student;
        if(string.IsNullOrEmpty(dto.Matricula)){
            student = _studentContext.student.Where(x => x.Email == dto.Email && x.Password == dto.Password).FirstOrDefault();
        } else{
            student = _studentContext.student.Where(x => x.Register == dto.Matricula && x.Password == dto.Password).FirstOrDefault();
        }

        return student;
    }

    public Coordinator? LoginCoordinator(LoginDTO dto){   
        Coordinator? coordinator = _coordinatorContext.Coordinator.Where(x => x.Email == dto.Email && x.Password == dto.Password).FirstOrDefault();
        return coordinator;
    }


}