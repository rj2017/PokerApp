package com.example.pokerapp.model

import com.example.pokerapp.model.DreamWorldModel
import com.example.pokerapp.model.OfficialArtworkModel
import com.google.gson.annotations.SerializedName

class OtherModel {

    @SerializedName("dream_world")
    val dream_world : DreamWorldModel =
        DreamWorldModel()

    @SerializedName("official-artwork")
    val official_artwork : OfficialArtworkModel =
        OfficialArtworkModel()
}