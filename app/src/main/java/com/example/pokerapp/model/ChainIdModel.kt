package com.example.pokerapp.model

import com.google.gson.annotations.SerializedName

class ChainIdModel {

    @SerializedName("evolution_chain")
    val urlChain : EvolutionChainModel = EvolutionChainModel()
}