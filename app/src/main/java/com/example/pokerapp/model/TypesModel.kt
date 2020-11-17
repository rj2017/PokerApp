package com.example.pokerapp.model

import com.example.pokerapp.model.CommunReturnModel
import com.google.gson.annotations.SerializedName

class TypesModel {

    @SerializedName("slot")
    val slot : Int = 0

    @SerializedName("type")
    val type : CommunReturnModel = CommunReturnModel()
}