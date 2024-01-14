package com.example.myscreensaver.network.pexel_model

import com.google.gson.annotations.SerializedName

data class ImagesPexel(
    @SerializedName("page"      ) var page     : Int?              = null,
    @SerializedName("per_page"  ) var perPage  : Int?              = null,
    @SerializedName("photos"    ) var photos   : ArrayList<SingleImagePexel> = arrayListOf(),
    @SerializedName("next_page" ) var nextPage : String?           = null
)
