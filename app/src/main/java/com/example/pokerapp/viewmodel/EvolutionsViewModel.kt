package com.example.pokerapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pokerapp.model.EvolutionModel
import com.example.pokerapp.rest.listeners.APIListener
import com.example.pokerapp.rest.repository.PokerRepository
import com.example.pokerapp.util.Resource
import java.lang.IllegalArgumentException

class EvolutionsViewModel(application: Application, val repository: PokerRepository) : AndroidViewModel(application) {

    private val mListEvolution = MutableLiveData<Resource<EvolutionModel>>()
    val listEvolution : LiveData<Resource<EvolutionModel>> = mListEvolution

    fun getEvolution(id : Int){
        repository.getIdChain(id, object : APIListener<EvolutionModel>{
            override fun onSucess(result: EvolutionModel, statusCode: Int) {
                mListEvolution.postValue(Resource(result))
            }

            override fun onFailure(message: String) {
                mListEvolution.postValue(Resource(null,IllegalArgumentException(message)))
            }

        })
    }

}