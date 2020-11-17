package com.example.pokerapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pokerapp.R
import com.example.pokerapp.model.DetailPokemonModel
import com.example.pokerapp.model.MainArrayListModel
import com.example.pokerapp.model.PokemonListModel
import com.example.pokerapp.rest.listeners.APIListener
import com.example.pokerapp.rest.repository.PokerRepository
import com.example.pokerapp.util.Resource
import com.example.pokerapp.util.UrlUtil
import kotlinx.android.synthetic.main.activity_pokemon_detail.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.lang.IllegalArgumentException

class MainViewModel(application: Application, val mRepository :PokerRepository) : AndroidViewModel(application){


    private val mListDetailPokemon = MutableLiveData<Resource<List<MainArrayListModel>>>()
    val listDetailPokemon : LiveData<Resource<List<MainArrayListModel>>> = mListDetailPokemon


    fun getAllPokemons(){
        var lista : MutableList<MainArrayListModel> = mutableListOf()
        mRepository.listAllPokemons()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ pokerList ->
                lista = pokerList.results
            },{
                e ->
                e.printStackTrace()
            },{
                mListDetailPokemon.value = Resource(lista)
            })
    }

    fun getPokemonByName(text : String){
        mRepository.getByName(text, object : APIListener<MainArrayListModel>{
            override fun onSucess(result: MainArrayListModel, statusCode: Int) {
                var lista : MutableList<MainArrayListModel> = mutableListOf()
                lista.add(result)
                mListDetailPokemon.value = Resource(lista)
            }

            override fun onFailure(message: String) {
                mListDetailPokemon.value = Resource(null, IllegalArgumentException(message))
            }

        })
    }



}