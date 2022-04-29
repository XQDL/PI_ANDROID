using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using API_DaHora.Models;
using API_DaHora.Models.DTO;
using API_DaHora.Services;


namespace API_DaHora.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    
    public class AuthenticateController : ControllerBase
    {
        private readonly AuthenticatorService _auth;

        public AuthenticateController(AuthenticatorService auth){         
            _auth = auth;
        }

        [HttpPost]
        public ActionResult<IUser> Login(LoginDTO loginDTO)
        {
            var user = _auth.Login(loginDTO);

            if(user != null){      
                try{
                    Student student = (Student) user;
                    return student;
                } catch (Exception){
                    Coordinator coordinator = (Coordinator) user;
                    return coordinator;
                }
            }
            return NotFound();
        }
    }
}