package com.example.pokerapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pokerapp.model.DamageModel
import com.example.pokerapp.rest.listeners.APIListener
import com.example.pokerapp.rest.repository.PokerRepository
import com.example.pokerapp.util.Resource

class StatsViewModel(application: Application, val repository: PokerRepository) : AndroidViewModel(application) {

    private val mListDamage = MutableLiveData<Resource<DamageModel>>()
    val listDamage : LiveData<Resource<DamageModel>> = mListDamage

    fun getDamageById(id : Int){
        repository.getDamageById(id, object : APIListener<DamageModel>{
            override fun onSucess(result: DamageModel, statusCode: Int) {
                mListDamage.value = Resource(result)
            }

            override fun onFailure(message: String) {
                mListDamage.value = Resource(null,Throwable(message))
            }

        })
    }
}