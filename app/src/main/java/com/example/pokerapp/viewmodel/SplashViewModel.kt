package com.example.pokerapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pokerapp.util.SecurityPreferences
import com.example.pokerapp.constants.PokeConstants

class SplashViewModel(application: Application) : AndroidViewModel(application) {

    private val mSecurityPreferences =
        SecurityPreferences(application)

    private val mLogado = MutableLiveData<Boolean>()
    val logado : LiveData<Boolean> = mLogado

    fun isLogado(){
        val logged = !mSecurityPreferences.getString(PokeConstants.SHARED.name).isNullOrBlank()
        mLogado.value = logged
    }
}