package com.app.tvmaze.model.people

import com.app.tvmaze.model.CountryModel
import com.app.tvmaze.model.ImageModel
import com.google.gson.annotations.SerializedName

class PersonModel (

    @SerializedName("id")
    var id: Int = -1,

    @SerializedName("url")
    var url: String = "",

    @SerializedName("name")
    var name: String = "",

    @SerializedName("country")
    var country: CountryModel = CountryModel(),

    @SerializedName("birthday")
    var birthday: String = "",

    @SerializedName("deathday")
    var deathDay: String? = null,

    @SerializedName("gender")
    var gender: String = "",

    @SerializedName("image")
    var image: ImageModel? = null
)