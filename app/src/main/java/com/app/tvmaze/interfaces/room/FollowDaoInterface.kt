package com.app.tvmaze.interfaces.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.tvmaze.model.room.FollowSeasonModel

@Dao
interface FollowDaoInterface {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFollow(follow: FollowSeasonModel)

    @Query("SELECT followed FROM follow_seasons where id = :id")
    suspend fun isEpisodeFollowed(id: Int): Boolean?

    @Query("UPDATE follow_seasons SET followed = :isFollowed where id = :id")
    suspend fun setEpisodeFollowed(id: Int, isFollowed: Boolean)
}