package com.example.pokerapp.model

import com.google.gson.annotations.SerializedName

data class CommunReturnModel (

    @SerializedName("name")
    val name : String = "",

    @SerializedName("url")
    val url: String =""
)