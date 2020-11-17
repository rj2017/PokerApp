package com.example.pokerapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pokerapp.constants.PokeConstants
import com.example.pokerapp.util.SecurityPreferences

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val mSecurityPreferences =
        SecurityPreferences(application)

    private val isLogged = MutableLiveData<Boolean>()
    val logged : LiveData<Boolean> = isLogged

    fun logar(name : String){
        mSecurityPreferences.store(PokeConstants.SHARED.name, name)

        isLogged.value = true
    }
}