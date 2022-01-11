package com.example.githubuser.logic.model

import com.google.gson.annotations.SerializedName

data class UnforkedRepoResponse(
    val name: String,
    val description: String,
    val fork: Boolean,
    val language: String,
    @SerializedName("stargazers_count") val stars: Int,
    @SerializedName("html_url") val url: String
)
