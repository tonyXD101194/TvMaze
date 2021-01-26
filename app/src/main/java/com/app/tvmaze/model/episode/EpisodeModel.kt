package com.app.tvmaze.model.episode

import com.app.tvmaze.model.ImageModel
import com.google.gson.annotations.SerializedName

class EpisodeModel (

    @SerializedName("id")
    var id: Int = -1,

    @SerializedName("url")
    var url: String = "",

    @SerializedName("name")
    var name: String = "",

    @SerializedName("season")
    var season: Int = -1,

    @SerializedName("number")
    var number: Int = -1,

    @SerializedName("type")
    var type: String = "",

    @SerializedName("airdate")
    var airDate: String = "",

    @SerializedName("airtime")
    var airTime: String = "",

    @SerializedName("airstamp")
    var airStamp: String = "",

    @SerializedName("runtime")
    var runtime: Int = -1,

    @SerializedName("image")
    var image: ImageModel = ImageModel(),

    @SerializedName("summary")
    var summary: String = ""
)