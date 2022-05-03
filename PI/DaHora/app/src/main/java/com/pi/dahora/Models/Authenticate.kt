package com.pi.dahora.Models

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

data class AuthenticateDTO (
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)




interface EndpointAuthenticate {
    @Headers("Content-Type: application/json")
    @POST("authenticate")
    fun authenticate(@Body userData: AuthenticateDTO) : Call<User>
}
