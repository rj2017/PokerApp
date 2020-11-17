package com.example.pokerapp.model

import com.google.gson.annotations.SerializedName

data class PokeList(

    val count : Int = 0,

    val results : MutableList<MainArrayListModel>
)