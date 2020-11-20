package com.example.pokerapp.util

object UrlUtil {

    fun getUrlForSearch(url : String) = url.substring(26)

    fun getIdForUrl(url : String) =  url.substring(42)
}