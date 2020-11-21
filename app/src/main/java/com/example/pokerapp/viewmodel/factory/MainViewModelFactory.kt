package com.example.pokerapp.viewmodel.factory

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokerapp.rest.repository.PokerRepository
import com.example.pokerapp.util.SecurityPreferences
import com.example.pokerapp.viewmodel.MainViewModel
import com.example.pokerapp.viewmodel.factory.exception.UnknownViewModelClassException

class MainViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(application, PokerRepository(application), SecurityPreferences(application)) as T
        }
        throw UnknownViewModelClassException()
    }
}