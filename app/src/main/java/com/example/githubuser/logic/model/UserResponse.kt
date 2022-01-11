package com.example.githubuser.logic.model

import com.google.gson.annotations.SerializedName

data class UserResponse(val items: List<Items>)

data class Items(
    val login: String,
    @SerializedName("avatar_url") val imageUrl: String
)
