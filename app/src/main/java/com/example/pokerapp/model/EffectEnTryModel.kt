package com.example.pokerapp.model

import com.google.gson.annotations.SerializedName

class EffectEnTryModel {

    @SerializedName("effect")
    val effect: String = ""

    @SerializedName("language")
    val language: CommunReturnModel = CommunReturnModel()

    @SerializedName("short_effect")
    val short_effect: String = ""
}