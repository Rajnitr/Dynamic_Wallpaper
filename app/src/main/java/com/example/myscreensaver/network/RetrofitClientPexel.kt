package com.example.myscreensaver.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientPexel {

    private const val BASE_URL = "https://api.pexels.com/v1/"

    fun getInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(RetrofitClientPexel.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val INSTANCE= getInstance().create(PexelApiService::class.java)
}