package com.app.tvmaze.model

import com.app.tvmaze.model.show.CountryModel
import com.google.gson.annotations.SerializedName

class NetworkModel (

    @SerializedName("id")
    var id: Int = -1,

    @SerializedName("name")
    var name: String = "",

    @SerializedName("country")
    var country: CountryModel = CountryModel()
)