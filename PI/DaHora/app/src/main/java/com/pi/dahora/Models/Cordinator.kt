package com.pi.dahora.Models

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET

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

interface Endpoint {

    @GET("api/coordinator")
    fun getCoordinator() : Call<List<Cordinator>>
}
