package com.app.tvmaze.model.season

import com.app.tvmaze.model.ImageModel
import com.app.tvmaze.model.NetworkModel
import com.google.gson.annotations.SerializedName

class SeasonModel (

    @SerializedName("id")
    var id: Int = -1,

    @SerializedName("url")
    var url: String = "",

    @SerializedName("number")
    var number: Int = -1,

    @SerializedName("name")
    var name: String = "",

    @SerializedName("episodeOrder")
    var episodeOrder: Int = -1,

    @SerializedName("premiereDate")
    var premiereDate: String = "",

    @SerializedName("endDate")
    var endDate: String = "",

    @SerializedName("network")
    var network: NetworkModel = NetworkModel(),

    @SerializedName("webChannel")
    var webChannel: Int? = null,

    @SerializedName("image")
    var image: ImageModel = ImageModel(),

    @SerializedName("summary")
    var summary: String = ""
)