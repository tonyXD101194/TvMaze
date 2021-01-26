package com.app.tvmaze.model.show

import com.google.gson.annotations.SerializedName

class ScheduleShowModel (

    @SerializedName("time")
    var time: String = "",

    @SerializedName("days")
    var days: Array<String> = arrayOf()
)