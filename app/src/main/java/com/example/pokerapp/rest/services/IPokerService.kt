package com.example.pokerapp.rest.services

import com.example.pokerapp.model.AbilitiesPokemonModel
import com.example.pokerapp.model.DamageModel
import com.example.pokerapp.model.DetailPokemonModel
import com.example.pokerapp.model.PokemonListModel
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface IPokerService {

    @GET("pokemon?limit=20&offset=00")
    fun listPokemons() : Observable<PokemonListModel>

    @GET("{url}")
    fun getPokemonbyUrl(@Path("url") url : String) : Observable<DetailPokemonModel>

    @GET("pokemon/{id}/")
    fun getPokemonsbyId(@Path("id") id : Int) : Call<DetailPokemonModel>

    @GET("type/{id}/")
    fun getDamageById(@Path("id") id : Int) : Call<DamageModel>

    @GET("pokemon/{name}")
    fun getPokemonByName(@Path("name") name : String) : Call<DetailPokemonModel>

    @GET("{url}")
    fun getAbilitiesbyUrl(@Path("url") url : String) : Call<AbilitiesPokemonModel>
}