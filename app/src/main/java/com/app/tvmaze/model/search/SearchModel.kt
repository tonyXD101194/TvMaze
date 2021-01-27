package com.app.tvmaze.model.search

import com.app.tvmaze.model.show.ShowModel
import com.google.gson.annotations.SerializedName

class SearchModel (

    @SerializedName("score")
    var score: Double = -1.0,

    @SerializedName("show")
    var show: ShowModel = ShowModel()
)