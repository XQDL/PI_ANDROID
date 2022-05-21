package com.pi.dahora.utils

import com.pi.dahora.Models.EndpointCoordinator
import com.pi.dahora.Models.User

class LoginUser {
    companion object{
        lateinit var userLogged : User
        var isCoordinator: Boolean = false
    }
}