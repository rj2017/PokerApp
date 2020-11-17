package com.example.pokerapp.model

import com.example.pokerapp.model.OtherModel
import com.google.gson.annotations.SerializedName

class SpritesModel {

    @SerializedName("back_default")
    val back_default : String = ""

    @SerializedName("back_female")
    val back_female : String = ""

    @SerializedName("back_shiny")
    val back_shiny : String = ""

    @SerializedName("back_shiny_female")
    val back_shiny_female : String = ""

    @SerializedName("front_default")
    val front_default : String = ""

    @SerializedName("front_female")
    val front_female : String = ""

    @SerializedName("front_shiny")
    val front_shiny : String = ""

    @SerializedName("front_shiny_female")
    val front_shiny_female : String = ""

    @SerializedName("other")
    val other : OtherModel = OtherModel()

    // TODO: 11/7/2020 "Verificar se há a necessidade de mapear as verssion por geração"
}