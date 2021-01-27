package com.app.tvmaze.model

import com.google.gson.annotations.SerializedName

class CountryModel (

    @SerializedName("name")
    var name: String = "",

    @SerializedName("code")
    var code: String = "",

    @SerializedName("timezone")
    var timezone: String = ""
)