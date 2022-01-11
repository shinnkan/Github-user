package com.example.githubuser.logic.network

import com.example.githubuser.logic.model.RepoResponse
import com.example.githubuser.logic.model.UnforkedRepoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RepoService {

    @GET("users/{username}")
    fun getRepos(@Path("username") userName: String): Call<RepoResponse>

    @GET("users/{username}/repos")
    fun getUserRepos(@Path("username") userName: String): Call<List<UnforkedRepoResponse>>
}