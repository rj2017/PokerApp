package com.example.pokerapp.model

import com.google.gson.annotations.SerializedName

class AbilitiesModel {

    @SerializedName("ability")
    val communReturn : CommunReturnModel = CommunReturnModel()

    @SerializedName("is_hidden")
    val is_hidden: Boolean = false

    @SerializedName("slot")
    val slot: Int = 0
}