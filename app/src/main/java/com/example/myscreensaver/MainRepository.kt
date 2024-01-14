package com.example.myscreensaver

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myscreensaver.network.PexelApiService
import com.example.myscreensaver.network.RetrofitClientPexel
import com.example.myscreensaver.network.pexel_model.ImagesPexel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainRepository(private val pexelApiService: PexelApiService) {
    private val TAG = "raj_dynamic_wallpaper"

    var imagesMutableLiveData= MutableLiveData<ImagesPexel> ()

    val imagesLiveData: LiveData<ImagesPexel>
        get() = imagesMutableLiveData

    suspend fun getImagesPexel() = pexelApiService.getCuratedImages()

    suspend fun getSearchedImagesPexel( query_s : String) = pexelApiService.getSearchedImages(query_s)

}