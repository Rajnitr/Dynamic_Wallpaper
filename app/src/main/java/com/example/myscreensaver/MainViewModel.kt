package com.example.myscreensaver

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myscreensaver.network.RetrofitClientPexel
import com.example.myscreensaver.network.pexel_model.ImagesPexel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val mainRepository: MainRepository): ViewModel() {

    private val TAG = "raj_dynamic_wallpaper"
    private var imagesMutableLiveData= MutableLiveData<ImagesPexel> ()

    val imagesLiveData: LiveData<ImagesPexel>
        get() = imagesMutableLiveData

    var categoryImageListMap = mutableMapOf<String, ImagesPexel>()

    fun getPexelImages() {
        CoroutineScope(Dispatchers.IO).launch {
            val results= mainRepository.getImagesPexel()
            if(results.isSuccessful) {
                Log.d(TAG, "getImagesPexel: msg=" +results.body())
                results?.body()?.let { categoryImageListMap?.put("All", it) }
                imagesMutableLiveData.postValue(results.body())

            }
            else {
                //Todo : handle error as per ur requirement
                Log.d(TAG, "getImagesPexel: failure")
            }
        }
    }

    fun getPexelImageForSearch(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val results= mainRepository.getSearchedImagesPexel(query)
            if(results.isSuccessful) {
                Log.d(TAG, "getImagesPexelSearch: msg=" +results.body())
                results?.body()?.let { categoryImageListMap?.put(query, it) }
                imagesMutableLiveData.postValue(results.body())

            }
            else {
                //Todo : handle error as per ur requirement
                Log.d(TAG, "getImagesPexel: failure")
            }
        }
    }

}