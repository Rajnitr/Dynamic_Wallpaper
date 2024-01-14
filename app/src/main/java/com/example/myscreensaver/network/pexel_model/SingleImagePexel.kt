package com.example.myscreensaver.network.pexel_model

import com.google.gson.annotations.SerializedName

data class SingleImagePexel(
    @SerializedName("id"               ) var id              : Int?     = null,
    @SerializedName("width"            ) var width           : Int?     = null,
    @SerializedName("height"           ) var height          : Int?     = null,
    @SerializedName("url"              ) var url             : String?  = null,
    @SerializedName("photographer"     ) var photographer    : String?  = null,
    @SerializedName("photographer_url" ) var photographerUrl : String?  = null,
    @SerializedName("photographer_id"  ) var photographerId  : Int?     = null,
    @SerializedName("avg_color"        ) var avgColor        : String?  = null,
    @SerializedName("src"              ) var src             : SingleImageSrcPexel?     = SingleImageSrcPexel(),
    @SerializedName("liked"            ) var liked           : Boolean? = null,
    @SerializedName("alt"              ) var alt             : String?  = null
)
