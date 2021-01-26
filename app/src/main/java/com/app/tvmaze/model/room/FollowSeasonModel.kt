package com.app.tvmaze.model.room

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "follow_seasons")
data class FollowSeasonModel (

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int = -1,

    @ColumnInfo(name = "followed")
    var favorite: Boolean? = false
)