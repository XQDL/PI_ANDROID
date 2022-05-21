package com.pi.dahora.Models

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.*
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
    var type: String
) {
    override fun toString(): String {
        return "Requirement(id=$id, tittle='$tittle', startDate='$startDate', endDate='$endDate', workLoad=$workLoad, comments=$comments, attachmentAdress=$attachmentAdress, institutionName='$institutionName', createdTime='$createdTime', approvedTime=$approvedTime, reason=$reason, student=$student, attachment=${attachment?.contentToString()}, type='$type')"
    }
}


interface EndpointRequirement {
    @GET("requirement")
    fun getRequirements() : Call<List<Requirement>>

    @POST("requirement")
    fun createRequirement(@Body requeriment: Requirement) : Call<Requirement>

    @PUT("requirement/{id}")
    fun updateRequirement(@Body requeriment: Requirement, @Path("id") id : Long) : Call<Requirement>
}
