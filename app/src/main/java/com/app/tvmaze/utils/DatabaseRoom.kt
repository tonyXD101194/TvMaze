package com.app.tvmaze.utils

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.tvmaze.interfaces.room.FollowDaoInterface
import com.app.tvmaze.model.room.FollowSeasonModel

@Database(
    entities = [FollowSeasonModel::class],
    version = 1
)
abstract class DatabaseRoom: RoomDatabase() {

    abstract fun followDao(): FollowDaoInterface

    companion object {

        private const val DATABASE_NAME = "tv_maze_db"

        @Volatile
        private var INSTANCE: DatabaseRoom? = null

        fun getInstance(context: Context): DatabaseRoom? {

            INSTANCE ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseRoom::class.java,
                    DATABASE_NAME
                ).build()
            }
            return INSTANCE
        }
    }
}