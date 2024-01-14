package com.example.myscreensaver.network

import com.example.myscreensaver.network.pexel_model.ImagesPexel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PexelApiService {
    @GET("curated")
    suspend fun getCuratedImages(
        @Header("Authorization") authorization: String= "ZUZ1tmEskXZyRk7QqPvVcPKpJyQkPwnzt2aA5377lTLrhlGiRxcWnjfr" ,
        @Query("page") page: Int? = 1, @Query("per_page") per_page: Int? = 80
    ) : Response<ImagesPexel>

    @GET("search")
    suspend fun getSearchedImages(
        @Query("query") query: String,
        @Header("Authorization") authorization: String= "ZUZ1tmEskXZyRk7QqPvVcPKpJyQkPwnzt2aA5377lTLrhlGiRxcWnjfr",
        @Query("page") page: Int? = 1, @Query("per_page") per_page: Int? = 80
    ) : Response<ImagesPexel>
}