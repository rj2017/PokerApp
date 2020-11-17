package com.example.pokerapp.rest.repository.remote

import com.example.pokerapp.constants.PokeConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {

        private lateinit var retrofit: Retrofit
        private const val BASE_URL = "https://pokeapi.co/api/v2/"
        private var personKey: String = ""
        private var tokenKey: String = ""

        private fun getRetrofitInstance(): Retrofit {

            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request =
                        chain.request()
                            .newBuilder()
                            .addHeader(
                                PokeConstants.HEADER.PERSON_KEY,
                                personKey
                            )
                            .addHeader(
                                PokeConstants.HEADER.TOKEN_KEY,
                                tokenKey
                            )
                            .build()
                    return chain.proceed(request)
                }
            })

            if (!Companion::retrofit.isInitialized) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }

        fun addHeaders(person: String, token: String) {
            personKey = person
            tokenKey = token
        }

        fun <S> createService(serviceClass: Class<S>): S {
            return getRetrofitInstance()
                .create(serviceClass)
        }
    }
}