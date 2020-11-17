package com.example.pokerapp.util

object UrlUtil {

    fun getUrlForSearch(url : String) : String{
        return url.substring(26)
    }
}