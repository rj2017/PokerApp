package com.example.pokerapp.rest.repository

import android.content.Context
import com.example.pokerapp.R
import com.example.pokerapp.model.*
import com.example.pokerapp.rest.listeners.APIListener
import com.example.pokerapp.rest.repository.remote.RetrofitClient
import com.example.pokerapp.rest.services.IPokerService
import com.example.pokerapp.util.Resource
import com.example.pokerapp.util.UrlUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import rx.Observable
import java.lang.IllegalArgumentException
import kotlin.collections.ArrayList

class PokerRepository(context: Context) : BaseRepositoy(context){

    private val mRemote = RetrofitClient.createService(IPokerService::class.java)
    private var mResultDetail : String = ""

    private var lista = mutableListOf<DetailPokemonModel>()

    fun listAllPokemons(listener : APIListener<PokemonListModel>){

        val call : Call<PokemonListModel> = mRemote.listPokemons()
        call.enqueue(object : Callback<PokemonListModel>{
            override fun onFailure(call: Call<PokemonListModel>, t: Throwable) {
                listener.onFailure(mContext.getString(R.string.ERROR_UNEXPECTED))
            }

            override fun onResponse(
                call: Call<PokemonListModel>,
                response: Response<PokemonListModel>
            ) {
                val code =  response.code()
                if (fail(code)) {
                    listener.onFailure(mContext.getString(R.string.ERROR_LOAD_POKEMON))
                } else {
                    response.body()?.let {  listener.onSucess(it,code)}
                }
            }

        })
    }

    suspend fun getPokemonsByUrl(url : String) : Resource<DetailPokemonModel>{

        var listDetailPokemon : Resource<DetailPokemonModel> = Resource(null)
        val result = mRemote.getPokemonbyUrl(url)
        if (result.isSuccessful){
            val code =  result.code()
            if (fail(code)) {
                listDetailPokemon = Resource(null, IllegalArgumentException(mContext.getString(R.string.ERROR_LOAD_POKEMON)))
            } else {
                result.body()?.let {  listDetailPokemon = Resource(it)}
            }
        }else{
            listDetailPokemon = Resource(null, IllegalArgumentException(mContext.getString(R.string.ERROR_UNEXPECTED)))
        }
        return listDetailPokemon
    }

    /*fun listAllPokemons() : Observable<PokeList>{
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

    }*/

    /*fun getPokemonById(id : Int, listener : APIListener<DetailPokemonModel>){
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
*/

    suspend fun getPokemonById(id : Int, listener : APIListener<DetailPokemonModel>){
      val result  = mRemote.getPokemonsbyId(id)

        if (result.isSuccessful){
            val code =  result.code()
            if (fail(code)) {
                listener.onFailure(mContext.getString(R.string.ERROR_LOAD_POKEMON))
            } else {
                result.body()?.let {  listener.onSucess(it,code)}
            }
        }else {
            listener.onFailure(mContext.getString(R.string.ERROR_UNEXPECTED))

        }
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

        val response = mRemote.getAbilitiesbyUrl(urlSearch)
        if(response.isSuccessful){
            val code =  response.code()
            if (fail(code)) {
                listener.onFailure(mContext.getString(R.string.ERROR_LOAD_POKEMON))
            } else {
                response.body()?.let { listener.onSucess(it,code) }
            }
        }else{
            listener.onFailure(mContext.getString(R.string.ERROR_UNEXPECTED))
        }

    }

    fun getIdChain(id : Int,listener: APIListener<EvolutionModel>){

        CoroutineScope(Dispatchers.Main).launch {
            val response = mRemote.getChainId(id)
            if (response.isSuccessful) {
                val code = response.code()
                if (fail(code)) {
                    listener.onFailure(mContext.getString(R.string.ERROR_LOAD_POKEMON))
                } else {
                    response.body()?.let { val result = getEvolution(it.urlChain.url)
                    if (result.isSucess()){
                        listener.onSucess(result = result.data!!,statusCode = code)
                    }else{
                        listener.onFailure(mContext.getString(R.string.ERROR_LOAD_POKEMON))
                    }
                    }
                }
            } else {
                listener.onFailure(mContext.getString(R.string.ERROR_UNEXPECTED))
            }
        }
    }

   suspend fun getEvolution(urlChain : String) : Resource<EvolutionModel>{

       var evolution : Resource<EvolutionModel> = Resource(null)

       val urlSearch = UrlUtil.getUrlForSearch(urlChain)
        val response = mRemote.getEvolutionsByUrl(urlSearch)
        if (response.isSuccessful){
            val code =  response.code()
            if (fail(code)) {
                evolution = Resource(null,IllegalArgumentException(mContext.getString(R.string.ERROR_LOAD_POKEMON)))
            } else {
                response.body()?.let {  evolution = Resource(it) }
            }
        }else{
            evolution = Resource(null,IllegalArgumentException(mContext.getString(R.string.ERROR_UNEXPECTED)))
        }

       return evolution
    }
}