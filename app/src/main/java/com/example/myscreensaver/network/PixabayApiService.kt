package com.example.myscreensaver.network

import com.example.myscreensaver.network.data.Images
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApiService {
    @GET("/api")
    suspend fun getImages(
        @Query("key") key: String = "39075471-061c40a0b606c525ec8686d8d"
    ) : Response<Images>
}