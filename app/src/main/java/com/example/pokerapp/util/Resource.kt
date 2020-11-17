package com.example.pokerapp.util

open class Resource<T>(
    val data : T?,
    val error: Throwable? = null
) {

    fun isSucess(): Boolean{
        return !isFailure()
    }

    private fun isFailure() : Boolean{
        return error != null
    }

    override fun toString(): String {
        return "Resource {data = ${data.toString()}, error= ${error.toString()}}"
    }
}