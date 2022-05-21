package com.pi.dahora.Models

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

data class Course(
    @SerializedName("id")
    var id : Int,
    @SerializedName("name")
    var name : String,
    @SerializedName("additionalHoursTarget")
    var additionalHoursTarget : Double,
    @SerializedName("coordinatorId")
    var coordinator : Long,

    )

interface EndpointCourse {
    @GET("course/{id}")
    fun getCourseById(@Path("id") id : Long) : Call<Course>
}
