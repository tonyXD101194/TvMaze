package com.app.tvmaze.model.show

import com.google.gson.annotations.SerializedName

class ExternalsShowModel (

    @SerializedName("tvrage")
    var tvrage: Int = -1,

    @SerializedName("thetvdb")
    var thetvdb: Int = -1,

    @SerializedName("imdb")
    var imdb: String = ""
)