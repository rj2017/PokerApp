package com.example.pokerapp.model

import com.example.pokerapp.model.CommunReturnModel
import com.google.gson.annotations.SerializedName

class GameIndiciesModel {

    @SerializedName("game_index")
    val game_index : Int = 0

    @SerializedName("version")
    val version : CommunReturnModel = CommunReturnModel()
}