package com.pi.dahora.Models

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET

data class Course(
    @SerializedName("id")
    var id : Int,
    @SerializedName("name")
    var name : String,
    @SerializedName("additionalHoursTarget")
    var additionalHoursTarget : Double,
    @SerializedName("coordinator")
    var coordinator : Cordinator,

    )

interface EndpointCourse {
    @GET("api/course")
    fun getCourses() : Call<List<Course>>
}
