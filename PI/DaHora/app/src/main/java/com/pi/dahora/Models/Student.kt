package com.pi.dahora.Models

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

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
    @GET("student")
    fun getStudents() : Call<List<Student>>

    @GET("student/{id}")
    fun getStudentById(@Path("id") id : Long) : Call<Student>

    @PUT("student/{id}")
    fun attStudent(@Body student: Student, @Path("id") id : Long) : Call<Student>

}
