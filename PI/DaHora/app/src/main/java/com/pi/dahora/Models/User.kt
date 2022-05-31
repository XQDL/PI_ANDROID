package com.pi.dahora.Models

import com.google.gson.annotations.SerializedName


data class User(
    @SerializedName("id")
    var id : Int,
    @SerializedName("name")
    var name : String?,
    @SerializedName("email")
    var email : String?,
    @SerializedName("password")
    var password : String?,
    @SerializedName("register")
    var register : String?,
    @SerializedName("additionalHoursPerformed")
    var additionalHoursPerformed : Double?,
    @SerializedName("hasCompletedHours")
    var hasCompletedHours : Boolean?,
    @SerializedName("courseId")
    var courseId : Long?,
    @SerializedName("phoneNumber")
    var phoneNumber : String?
) {
    override fun toString(): String {
        return "User(id=$id, name=$name, email=$email, password=$password, register=$register, additionalHoursPerformed=$additionalHoursPerformed, hasCompletedHours=$hasCompletedHours, courseId=$courseId, phoneNumber=$phoneNumber)"
    }
}
