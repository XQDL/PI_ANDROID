package com.pi.dahora.Models

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

data class Cordinator(
    @SerializedName("id")
    var id : Int,
    @SerializedName("name")
    var name : String,
    @SerializedName("email")
    var email : String,
    @SerializedName("password")
    var password : String,
    @SerializedName("phoneNumber")
    var phoneNumber : String
    )

interface EndpointCoordinator {
    @GET("coordinator")
    fun getCoordinator() : Call<List<Cordinator>>


}

