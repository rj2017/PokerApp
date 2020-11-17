package com.example.pokerapp.model

import com.google.gson.annotations.SerializedName

class DamageModel {
    @SerializedName("damage_relations")
    val damage_relations: DamageDetailsModel = DamageDetailsModel()
}