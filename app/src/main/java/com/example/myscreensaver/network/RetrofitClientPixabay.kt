package com.example.myscreensaver.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientPixabay {

    // https://pixabay.com/api/?key=39075471-061c40a0b606c525ec8686d8d
    private const val BASE_URL = "https://pixabay.com/"

    fun getInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val INSTANCE= getInstance().create(PixabayApiService::class.java)
}