package com.example.pokerapp.model

import com.google.gson.annotations.SerializedName

data class MainArrayListModel(
    val id : Int = 0,

    val name : String = "",

    val sprites : SpritesModel = SpritesModel(),

    val types : List<TypesModel> = listOf(),

    val stats : List<StatusModel> = listOf(),

    val abilities : List<AbilitiesModel> = listOf()
)