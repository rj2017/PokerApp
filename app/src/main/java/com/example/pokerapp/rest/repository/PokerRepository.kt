package com.example.pokerapp.rest.repository

import android.content.Context
import android.net.Uri
import com.example.pokerapp.R
import com.example.pokerapp.model.*
import com.example.pokerapp.rest.listeners.APIListener
import com.example.pokerapp.rest.repository.remote.RetrofitClient
import com.example.pokerapp.rest.services.IPokerService
import com.example.pokerapp.util.UrlUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import rx.Observable
import java.util.*
import kotlin.collections.ArrayList

class PokerRepository(context: Context) : BaseRepositoy(context){

    private val mRemote = RetrofitClient.createService(IPokerService::class.java)
    private var mResultDetail : String = ""

    private var lista = mutableListOf<DetailPokemonModel>()

    fun listAllPokemons() : Observable<PokeList>{
        return mRemote.listPokemons()
            .flatMap { pokerResult ->
                Observable.zip(
                    Observable.just(PokeList(results = ArrayList<MainArrayListModel>())),
                    Observable.from(pokerResult.results)
                        .flatMap { pokersUrl ->
                            val urlSearch = UrlUtil.getUrlForSearch(pokersUrl.url)
                            mRemote.getPokemonbyUrl(urlSearch)
                        }
                        .map { detailPokemon ->
                            MainArrayListModel(id = detailPokemon.id, name = detailPokemon.name, types = detailPokemon.types,
                                sprites = detailPokemon.sprites, abilities = detailPokemon.abilities, stats = detailPokemon.stats)
                        }

                        .toList()
                ) { pokeList, pokemons ->
                    pokeList.results.addAll(pokemons)
                    pokeList
                }

            }

    }

    fun getPokemonById(id : Int, listener : APIListener<DetailPokemonModel>){
        val call : Call<DetailPokemonModel> = mRemote.getPokemonsbyId(id)
        call.enqueue(object : Callback<DetailPokemonModel>{
            override fun onFailure(call: Call<DetailPokemonModel>, t: Throwable) {
                listener.onFailure(mContext.getString(R.string.ERROR_UNEXPECTED))
            }

            override fun onResponse(
                call: Call<DetailPokemonModel>,
                response: Response<DetailPokemonModel>
            ) {
                val code =  response.code()
                if (fail(code)) {
                    listener.onFailure(response.errorBody()!!.string())
                } else {
                    response.body()?.let { listener.onSucess(it,code) }
                }
            }
        })
    }

    fun getDamageById(id: Int, listener: APIListener<DamageModel>){

        val call : Call<DamageModel> = mRemote.getDamageById(id)
        call.enqueue(object : Callback<DamageModel>{
            override fun onFailure(call: Call<DamageModel>, t: Throwable) {
                listener.onFailure(mContext.getString(R.string.ERROR_UNEXPECTED))
            }

            override fun onResponse(call: Call<DamageModel>, response: Response<DamageModel>) {
                val code =  response.code()
                if (fail(code)) {
                    listener.onFailure(response.errorBody()!!.string())
                } else {
                    response.body()?.let { listener.onSucess(it,code) }
                }
            }

        })
    }

    fun getByName(text : String, listener: APIListener<MainArrayListModel>){
        val call : Call<DetailPokemonModel> = mRemote.getPokemonByName(text)
        call.enqueue(object : Callback<DetailPokemonModel>{
            override fun onFailure(call: Call<DetailPokemonModel>, t: Throwable) {
                listener.onFailure(mContext.getString(R.string.ERROR_UNEXPECTED))
            }

            override fun onResponse(
                call: Call<DetailPokemonModel>,
                response: Response<DetailPokemonModel>
            ) {
                val code =  response.code()
                if (fail(code)) {
                    listener.onFailure(mContext.getString(R.string.ERROR_SEARCH))
                } else {
                    response.body()?.let { listener.onSucess(MainArrayListModel(id = it.id, name = it.name, types = it.types,
                        sprites = it.sprites, abilities = it.abilities, stats = it.stats),code) }
                }
            }

        })
    }

    suspend fun getAbilities(url : String, listener: APIListener<AbilitiesPokemonModel>){

        val urlSearch = UrlUtil.getUrlForSearch(url)

        val call : Call<AbilitiesPokemonModel> = mRemote.getAbilitiesbyUrl(urlSearch)

        call.enqueue(object : Callback<AbilitiesPokemonModel>{
            override fun onFailure(call: Call<AbilitiesPokemonModel>, t: Throwable) {
                listener.onFailure(mContext.getString(R.string.ERROR_UNEXPECTED))
            }

            override fun onResponse(
                call: Call<AbilitiesPokemonModel>,
                response: Response<AbilitiesPokemonModel>
            ) {
                val code =  response.code()
                if (fail(code)) {
                    listener.onFailure(mContext.getString(R.string.ERROR_LOAD_DAMAGE))
                } else {
                    response.body()?.let { listener.onSucess(it,code) }
                }
            }

        })
    }

}