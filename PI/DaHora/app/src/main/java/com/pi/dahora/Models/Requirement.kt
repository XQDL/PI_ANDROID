package com.pi.dahora.Models

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.*

data class Requirement(
    @SerializedName("id")
    var id: Int,
    @SerializedName("tittle")
    var tittle: String,
    @SerializedName("startDate")
    var startDate: String,
    @SerializedName("endDate")
    var endDate: String,
    @SerializedName("workLoad")
    var workLoad: Double,
    @SerializedName("comments")
    var comments: String?,
    @SerializedName("attachmentAdress")
    var attachmentAdress: String?,
    @SerializedName("institutionName")
    var institutionName: String,
    @SerializedName("createdTime")
    var createdTime: String,
    @SerializedName("approvedTime")
    var approvedTime: String?,
    @SerializedName("reason")
    var reason: String?,
    @SerializedName("studentId")
    var student: Long,
    @SerializedName("attachment")
    var attachment: ByteArray?,
    @SerializedName("type")
    var type: String,
    @SerializedName("status")
    var status : String?
)


interface EndpointRequirement {
    @GET("requirement")
    fun getRequirements() : Call<List<Requirement>>

    @POST("requirement")
    fun createRequirement(@Body requeriment: Requirement) : Call<Requirement>

}
