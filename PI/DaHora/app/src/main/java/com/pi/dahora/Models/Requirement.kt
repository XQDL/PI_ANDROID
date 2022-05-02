package com.pi.dahora.Models

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET

data class Requirement(
    @SerializedName("id")
    var id : Int,
    @SerializedName("tittle")
    var tittle : String,
    @SerializedName("startDate")
    var startDate : Datetime,
    @SerializedName("endDate")
    var endDate : Datetime,
    @SerializedName("workLoad")
    var workLoad : Double,
    @SerializedName("comments")
    var comments : String,
    @SerializedName("attachmentAdress")
    var attachmentAdress : String,
    @SerializedName("institutionName")
    var institutionName : string,
    @SerializedName("createdTime")
    var createdTime : Datetime,
    @SerializedName("approvedTime")
    var approvedTime : Datetime,
    @SerializedName("reason")
    var reason : string,
    @SerializedName("student")
    var student : Student
    )

interface Endpoint {
    @GET("api/student")
    fun getRequirements() : Call<List<Student>>
}
