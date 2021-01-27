package com.app.tvmaze.model.people

import com.app.tvmaze.model.show.ShowModel
import com.google.gson.annotations.SerializedName

class ShowPeopleModel (

    @SerializedName("show")
    var show: ShowModel = ShowModel()

)