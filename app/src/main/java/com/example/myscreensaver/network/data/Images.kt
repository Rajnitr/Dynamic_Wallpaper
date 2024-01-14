package com.example.myscreensaver.network.data

import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("total"     ) var total     : Long?            = null,
    @SerializedName("totalHits" ) var totalHits : Long?            = null,
    @SerializedName("hits"      ) var hits      : List<SingleImage>
)
