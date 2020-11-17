package com.example.pokerapp.model

import com.example.pokerapp.model.CommunReturnModel
import com.google.gson.annotations.SerializedName

class VersionGroupDetailsModel {

    @SerializedName("level_learned_at")
    val level_learned_at : Int = 0

    @SerializedName("move_learn_method")
    val move_learn_method : CommunReturnModel = CommunReturnModel()

    @SerializedName("version_group")
    val version_group : CommunReturnModel = CommunReturnModel()

}