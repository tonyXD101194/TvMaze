package com.app.tvmaze.repository

import android.app.Application
import com.app.tvmaze.interfaces.room.FollowDaoInterface
import com.app.tvmaze.model.room.FollowSeasonModel
import com.app.tvmaze.utils.DatabaseRoom

class FollowRepository(application: Application) {

    private val followDao: FollowDaoInterface? = DatabaseRoom.getInstance(application)?.followDao()

    suspend fun insertFollow(follow: FollowSeasonModel) {

        followDao!!.insertFollow(
            follow = follow
        )
    }

    suspend fun isEpisodeFollowed(id: Int): Boolean? {

        return followDao!!.isEpisodeFollowed(
            id = id
        )
    }

    suspend fun setEpisodeFollowed(id: Int, isFavorite: Boolean) {

        return followDao!!.setEpisodeFollowed(
            id = id,
            isFollowed = isFavorite
        )
    }
}