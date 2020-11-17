package com.example.pokerapp.model

import com.google.gson.annotations.SerializedName

data class PokemonListModel(

    @SerializedName("count")
    val count : Int = 0,

    @SerializedName("next")
    val next : String = "",

    @SerializedName("previous")
    val previous : String = "",

    @SerializedName("results")
    val results : List<CommunReturnModel> = listOf()

)