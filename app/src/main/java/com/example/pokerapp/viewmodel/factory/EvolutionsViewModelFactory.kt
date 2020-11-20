package com.example.pokerapp.viewmodel.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokerapp.rest.repository.PokerRepository
import com.example.pokerapp.viewmodel.AbilitiesViewModel
import com.example.pokerapp.viewmodel.EvolutionsViewModel
import com.example.pokerapp.viewmodel.factory.exception.UnknownViewModelClassException

class EvolutionsViewModelFactory(val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EvolutionsViewModel::class.java)){
            return EvolutionsViewModel(application, PokerRepository(application)) as T
        }
        throw UnknownViewModelClassException()
    }

}