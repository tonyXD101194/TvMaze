package com.app.tvmaze.model

import com.google.gson.annotations.SerializedName

class ImageModel (

    @SerializedName("medium")
    var medium: String = "",

    @SerializedName("original")
    var original: String = ""
)