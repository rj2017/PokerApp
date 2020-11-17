package com.example.pokerapp.model

import com.google.gson.annotations.SerializedName

class DetailPokemonModel {

    @SerializedName("abilities")
    val abilities : List<AbilitiesModel> = listOf()

    @SerializedName("base_experience")
    val base_experience : Int = 0

    @SerializedName("forms")
    val forms : List<CommunReturnModel> = listOf()

    @SerializedName("game_indices")
    val game_indices : List<GameIndiciesModel> = listOf()

    @SerializedName("height")
    val height : Int = 0

    @SerializedName("held_items")
    val held_items : List<Any>? = null

    @SerializedName("id")
    val id: Int = 0

    @SerializedName("is_default")
    val is_default : Boolean = false

    @SerializedName("location_area_encounters")
    val location_area_encounters_url : String = ""

    @SerializedName("moves")
    val moves : List<MovesModel> = listOf()

    @SerializedName("name")
    val name : String = ""

    @SerializedName("order")
    val order : Int = 1

    @SerializedName("species")
    val species : CommunReturnModel = CommunReturnModel()

    @SerializedName("sprites")
    val sprites : SpritesModel = SpritesModel()

    @SerializedName("stats")
    val stats : List<StatusModel> = listOf()

    @SerializedName("types")
    val types : List<TypesModel> = listOf()

    @SerializedName("weight")
    val weight : Int = 0

}