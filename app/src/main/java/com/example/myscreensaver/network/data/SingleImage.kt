package com.example.myscreensaver.network.data

import com.google.gson.annotations.SerializedName

data class SingleImage(
    @SerializedName("id"              ) var id              : Long?    = null,
    @SerializedName("pageURL"         ) var pageURL         : String? = null,
    @SerializedName("type"            ) var type            : String? = null,
    @SerializedName("tags"            ) var tags            : String? = null,
    @SerializedName("previewURL"      ) var previewURL      : String? = null,
    @SerializedName("previewWidth"    ) var previewWidth    : Long?    = null,
    @SerializedName("previewHeight"   ) var previewHeight   : Long?    = null,
    @SerializedName("webformatURL"    ) var webformatURL    : String? = null,
    @SerializedName("webformatWidth"  ) var webformatWidth  : Long?    = null,
    @SerializedName("webformatHeight" ) var webformatHeight : Long?    = null,
    @SerializedName("largeImageURL"   ) var largeImageURL   : String? = null,
    @SerializedName("imageWidth"      ) var imageWidth      : Long?    = null,
    @SerializedName("imageHeight"     ) var imageHeight     : Long?    = null,
    @SerializedName("imageSize"       ) var imageSize       : Long?    = null,
    @SerializedName("views"           ) var views           : Long?    = null,
    @SerializedName("downloads"       ) var downloads       : Long?    = null,
    @SerializedName("collections"     ) var collections     : Long?    = null,
    @SerializedName("likes"           ) var likes           : Long?    = null,
    @SerializedName("comments"        ) var comments        : Long?    = null,
    @SerializedName("user_id"         ) var userId          : Long?    = null,
    @SerializedName("user"            ) var user            : String? = null,
    @SerializedName("userImageURL"    ) var userImageURL    : String? = null
)
