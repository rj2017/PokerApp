package com.example.pokerapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pokerapp.model.DetailPokemonModel
import com.example.pokerapp.model.MainArrayListModel
import com.example.pokerapp.rest.listeners.APIListener
import com.example.pokerapp.rest.repository.PokerRepository
import com.example.pokerapp.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val mRepository : PokerRepository = PokerRepository(application)

    private val mListDetailPokemon = MutableLiveData<Resource<DetailPokemonModel>>()
    val listDetailPokemon : LiveData<Resource<DetailPokemonModel>> = mListDetailPokemon

    fun getPokemonById(id : Int){

        CoroutineScope(Dispatchers.Main).launch{
        mRepository.getPokemonById(id, object : APIListener<DetailPokemonModel>{
            override fun onSucess(result: DetailPokemonModel, statusCode: Int) {
                mListDetailPokemon.value = Resource(data = result)
            }

            override fun onFailure(message: String) {
                mListDetailPokemon.value = Resource(null, IllegalArgumentException(message))
            }
        })
        }
    }

}