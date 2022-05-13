package com.pi.dahora.Models

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET

data class Student(
    @SerializedName("id")
     var id : Int,
    @SerializedName("name")
     var name : String,
    @SerializedName("email")
     var email : String,
    @SerializedName("password")
     var password : String,
    @SerializedName("register")
    var register : String,
    @SerializedName("additionalHoursPerformed")
    var additionalHoursPerformed : Double,
    @SerializedName("hasCompletedHoursster")
    var hasCompletedHours : Boolean,
    @SerializedName("courseId")
    var course : Long,
    )
interface EndpointStudent {
    @GET("api/student")
    fun getStudents() : Call<List<Student>>
}
