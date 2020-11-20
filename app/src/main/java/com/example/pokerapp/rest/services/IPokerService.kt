package com.example.pokerapp.rest.services

import com.example.pokerapp.model.*
import retrofit2.Call
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface IPokerService {

    @GET("pokemon?limit=50&offset=00")
    fun listPokemons() : Call<PokemonListModel>

    @GET("{url}")
    suspend fun getPokemonbyUrl(@Path("url") url : String) : Response<DetailPokemonModel>

    @GET("pokemon/{id}/")
    suspend fun getPokemonsbyId(@Path("id") id : Int) : Response<DetailPokemonModel>

    @GET("type/{id}/")
    fun getDamageById(@Path("id") id : Int) : Call<DamageModel>

    @GET("pokemon/{name}")
    fun getPokemonByName(@Path("name") name : String) : Call<DetailPokemonModel>

    @GET("pokemon-species/{id}/")
    suspend fun getChainId(@Path("id") id : Int) : Response<ChainIdModel>

    @GET("{url}")
    suspend fun getEvolutionsByUrl(@Path("url") url : String) : Response<EvolutionModel>

    @GET("{url}")
    suspend fun getAbilitiesbyUrl(@Path("url") url : String) : Response<AbilitiesPokemonModel>
}