package com.example.githubbrowseapplication.model

import com.google.gson.annotations.SerializedName

data class Owner(

    @SerializedName("avatar_url")
    val avatarUrl: String,
    val login: String,
    val type: String,
    val url: String,
    val html_url: String,
    val score: Double
)