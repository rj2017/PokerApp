package com.example.pokerapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pokerapp.constants.PokeConstants
import com.example.pokerapp.util.SecurityPreferences

class LoadLoginViewModel(application: Application) : AndroidViewModel(application) {

    private val mSecurityPreferences =
        SecurityPreferences(application)

    private val mName = MutableLiveData<String>()
    val name : LiveData<String> = mName

    fun getNameUser(){
        val name = mSecurityPreferences.getString(PokeConstants.SHARED.name)
        mName.value = name
    }
}