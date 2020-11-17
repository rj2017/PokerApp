package com.example.pokerapp.rest.listeners

class ValidationListener(errorMessage : String ="") {
    private var mStatus: Boolean = true
    private var mValidationMessage: String = ""

    init {
        if (errorMessage != ""){
            mStatus = false
            mValidationMessage = errorMessage
        }
    }

    fun sucess() = mStatus
    fun failure() = mValidationMessage
}