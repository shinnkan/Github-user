package com.example.githubuser.logic.model

import com.google.gson.annotations.SerializedName

data class RepoResponse(
    val login: String,
    @SerializedName("avatar_url") val imageUrl: String,
    val name: String,
    val followers: Int,
    val following: Int
)
