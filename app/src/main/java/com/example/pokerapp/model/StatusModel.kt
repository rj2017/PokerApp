package com.example.pokerapp.model

import com.example.pokerapp.model.CommunReturnModel
import com.google.gson.annotations.SerializedName

class StatusModel {
    @SerializedName("base_stat")
    val base_stat : Int = 0

    @SerializedName("effort")
    val effort : Int = 0

    @SerializedName("stat")
    val stat : CommunReturnModel = CommunReturnModel()

}