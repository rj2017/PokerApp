package com.example.pokerapp.rest.listeners

interface APIListener<T> {
    fun onSucess(result: T , statusCode : Int)
    fun onFailure(message: String)
}