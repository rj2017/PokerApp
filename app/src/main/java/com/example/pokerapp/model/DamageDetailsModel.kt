package com.example.pokerapp.model

import com.google.gson.annotations.SerializedName

class DamageDetailsModel {

    @SerializedName("double_damage_from")
    val doubleDamageFrom : List<CommunReturnModel> = listOf()

    @SerializedName("double_damage_to")
    val doubleDamageTo : List<CommunReturnModel> = listOf()

    @SerializedName("half_damage_from")
    val halfDamageFrom : List<CommunReturnModel> = listOf()

    @SerializedName("half_damage_to")
    val halfDamageTo : List<CommunReturnModel> = listOf()
}