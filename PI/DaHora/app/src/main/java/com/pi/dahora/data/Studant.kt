package com.pi.dahora.data

data class Studant(
    val id: Int,
    val name: String,
    val register: String,
    val email: String,
    val password: String,
    val additional_hours_performed: Int,
    val has_completed_hours: Boolean
)
