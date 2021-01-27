package com.app.tvmaze.model.people

import com.google.gson.annotations.SerializedName

class PeopleShowsModel (

    @SerializedName("self")
    var self: Boolean = false,

    @SerializedName("voice")
    var voice: Boolean = false,

    @SerializedName("_embedded")
    var show: ShowPeopleModel = ShowPeopleModel()
)