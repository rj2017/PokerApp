package com.example.pokerapp.viewmodel.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokerapp.rest.repository.PokerRepository
import com.example.pokerapp.viewmodel.AbilitiesViewModel
import com.example.pokerapp.viewmodel.MainViewModel
import com.example.pokerapp.viewmodel.factory.exception.UnknownViewModelClassException

class AbilitiesViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AbilitiesViewModel::class.java)){
            return AbilitiesViewModel(application, PokerRepository(application)) as T
        }
        throw UnknownViewModelClassException()
    }
}