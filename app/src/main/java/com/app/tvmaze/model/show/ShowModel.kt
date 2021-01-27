package com.app.tvmaze.model.show

import com.app.tvmaze.model.ImageModel
import com.app.tvmaze.model.NetworkModel
import com.google.gson.annotations.SerializedName

class ShowModel (

    @SerializedName("id")
    var id: Int = -1,

    @SerializedName("url")
    var url: String = "",

    @SerializedName("name")
    var name: String = "",

    @SerializedName("type")
    var type: String = "",

    @SerializedName("language")
    var language: String = "",

    @SerializedName("genres")
    var genres: Array<String> = arrayOf(),

    @SerializedName("status")
    var status: String = "",

    @SerializedName("runtime")
    var runtime: Int = -1,

    @SerializedName("premiered")
    var premiered: String = "",

    @SerializedName("officialSite")
    var officialSite: String = "",

    @SerializedName("schedule")
    var schedule: ScheduleShowModel = ScheduleShowModel(),

    @SerializedName("rating")
    var rating: RatingShowModel = RatingShowModel(),

    @SerializedName("weight")
    var weight: String = "",

    @SerializedName("network")
    var network: NetworkModel = NetworkModel(),

    @SerializedName("externals")
    var externals: ExternalsShowModel = ExternalsShowModel(),

    @SerializedName("image")
    var image: ImageModel? = null,

    @SerializedName("summary")
    var summary: String = "",

    @SerializedName("updated")
    var updated: Long = -1,

    var isFavorite: Boolean = false
)