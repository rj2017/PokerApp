package com.example.pokerapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pokerapp.model.AbilitiesPokemonModel
import com.example.pokerapp.rest.listeners.APIListener
import com.example.pokerapp.rest.repository.PokerRepository
import com.example.pokerapp.util.Resource
import kotlinx.coroutines.*

class AbilitiesViewModel(application: Application, val repository: PokerRepository) : AndroidViewModel(application) {

    private val mListAbilities = MutableLiveData<Resource<List<AbilitiesPokemonModel>>>()
    val listAbilities : LiveData<Resource<List<AbilitiesPokemonModel>>> = mListAbilities

    fun getAbilities(lista : List<String>){

        val listaRetorno = mutableListOf<AbilitiesPokemonModel>()

        GlobalScope.launch(Dispatchers.Main){
                lista.forEach {
                    repository.getAbilities(it, object : APIListener<AbilitiesPokemonModel> {
                        override fun onSucess(result: AbilitiesPokemonModel, statusCode: Int) {
                            listaRetorno.add(result)
                        }

                        override fun onFailure(message: String) {
                            mListAbilities.value = Resource(null, IllegalArgumentException(message))
                        }

                    })
                }

            mListAbilities.value  = Resource(listaRetorno)
        }

    }
}