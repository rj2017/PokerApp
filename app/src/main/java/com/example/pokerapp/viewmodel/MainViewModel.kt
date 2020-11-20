package com.example.pokerapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pokerapp.R
import com.example.pokerapp.constants.PokeConstants
import com.example.pokerapp.model.DetailPokemonModel
import com.example.pokerapp.model.MainArrayListModel
import com.example.pokerapp.model.PokemonListModel
import com.example.pokerapp.rest.listeners.APIListener
import com.example.pokerapp.rest.repository.PokerRepository
import com.example.pokerapp.util.Resource
import com.example.pokerapp.util.SecurityPreferences
import com.example.pokerapp.util.UrlUtil
import kotlinx.android.synthetic.main.activity_pokemon_detail.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.lang.IllegalArgumentException

class MainViewModel(application: Application, val mRepository: PokerRepository) :
    AndroidViewModel(application) {

    private val mSecurityPreferences =
        SecurityPreferences(application)

    private val isLogged = MutableLiveData<Boolean>()
    val logged : LiveData<Boolean> = isLogged

    private val mListDetailPokemon = MutableLiveData<Resource<List<MainArrayListModel>>>()
    val listDetailPokemon: LiveData<Resource<List<MainArrayListModel>>> = mListDetailPokemon


    fun getAllPokemons() {
        var lista: MutableList<MainArrayListModel> = mutableListOf()

            mRepository.listAllPokemons(object : APIListener<PokemonListModel> {
                override fun onSucess(result: PokemonListModel, statusCode: Int) {
                    GlobalScope.launch(Dispatchers.IO){
                    result.results.forEach {
                        val urlSearch = UrlUtil.getUrlForSearch(it.url)
                        val pokemonsDetail = mRepository.getPokemonsByUrl(urlSearch)
                        if (pokemonsDetail.isSucess()){
                            lista.add(MainArrayListModel(id = pokemonsDetail.data!!.id, name = pokemonsDetail.data!!.name, types = pokemonsDetail.data!!.types,
                                sprites = pokemonsDetail.data!!.sprites, abilities = pokemonsDetail.data!!.abilities, stats = pokemonsDetail.data!!.stats))
                        }
                    }
                    }
                    mListDetailPokemon.value = Resource(lista)
                }

                override fun onFailure(message: String) {
                    mListDetailPokemon.value = Resource(null, IllegalArgumentException(message))
                }

            })

    }



    fun getPokemonByName(text: String) {
        mRepository.getByName(text, object : APIListener<MainArrayListModel> {
            override fun onSucess(result: MainArrayListModel, statusCode: Int) {
                var lista: MutableList<MainArrayListModel> = mutableListOf()
                lista.add(result)
                mListDetailPokemon.value = Resource(lista)
            }

            override fun onFailure(message: String) {
                mListDetailPokemon.value = Resource(null, IllegalArgumentException(message))
            }
        })
    }

    fun exitApp() {
        mSecurityPreferences.remove(PokeConstants.SHARED.name)
        isLogged.value = false
    }

    fun islogged(){
        val name = mSecurityPreferences.getString(PokeConstants.SHARED.name)
        if ( name.isNullOrEmpty()){
            isLogged.value = true
        }
    }


}