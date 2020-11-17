package com.example.pokerapp.model

import com.google.gson.annotations.SerializedName

class MovesModel {

    @SerializedName("move")
    val move : CommunReturnModel = CommunReturnModel()

    @SerializedName("version_group_details")
    val version_group_details : List<VersionGroupDetailsModel> = listOf()
}