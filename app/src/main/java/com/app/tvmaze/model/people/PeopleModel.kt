package com.app.tvmaze.model.people

import com.google.gson.annotations.SerializedName

class PeopleModel (

    @SerializedName("score")
    var score: Double = -1.0,

    @SerializedName("person")
    var person: PersonModel = PersonModel()
)