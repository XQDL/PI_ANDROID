package com.pi.dahora.Models

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import java.util.*

data class Requirement(
    @SerializedName("id")
    var id : Int,
    @SerializedName("tittle")
    var tittle : String,
    @SerializedName("startDate")
    var startDate : Date,
    @SerializedName("endDate")
    var endDate : Date,
    @SerializedName("workLoad")
    var workLoad : Double,
    @SerializedName("comments")
    var comments : String,
    @SerializedName("attachmentAdress")
    var attachmentAdress : String,
    @SerializedName("institutionName")
    var institutionName : String,
    @SerializedName("createdTime")
    var createdTime : Date,
    @SerializedName("approvedTime")
    var approvedTime : Date,
    @SerializedName("reason")
    var reason : String,
    @SerializedName("student")
    var student : Student
    )

interface EndpointRequirement {
    @GET("api/requirement")
    fun getRequirements() : Call<List<Requirement>>
}
