package com.example.pokerapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pokerapp.model.AbilitiesPokemonModel
import com.example.pokerapp.rest.listeners.APIListener
import com.example.pokerapp.rest.repository.PokerRepository
import com.example.pokerapp.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AbilitiesViewModel(application: Application, val repository: PokerRepository) : AndroidViewModel(application) {

    private val mListAbilities = MutableLiveData<Resource<List<AbilitiesPokemonModel>>>()
    val listAbilities : LiveData<Resource<List<AbilitiesPokemonModel>>> = mListAbilities

    fun getAbilities(lista : HashMap<String,String>){

        val listaRetorno = mutableListOf<AbilitiesPokemonModel>()

        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.Default) {
                lista.forEach {
                    repository.getAbilities(it.value, object : APIListener<AbilitiesPokemonModel> {
                        override fun onSucess(result: AbilitiesPokemonModel, statusCode: Int) {
                            listaRetorno.add(result)
                        }

                        override fun onFailure(message: String) {
                            mListAbilities.value = Resource(null, IllegalArgumentException(message))
                        }

                    })
                }
            }
            mListAbilities.postValue(Resource(listaRetorno))
        }
    }
}