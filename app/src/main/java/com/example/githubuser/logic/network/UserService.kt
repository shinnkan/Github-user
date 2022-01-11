package com.example.currencyconversion.logic.network

import com.example.githubuser.logic.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {

    @GET("search/users")
    fun getUsers(@Query("q") userName: String): Call<UserResponse>

}