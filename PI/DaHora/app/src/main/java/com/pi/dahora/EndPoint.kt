package com.pi.dahora

import com.pi.dahora.model.Posts
import retrofit2.Call
import retrofit2.http.GET


interface Endpoint {

    @GET("posts")
    fun getPosts() : Call<List<Posts>>
}