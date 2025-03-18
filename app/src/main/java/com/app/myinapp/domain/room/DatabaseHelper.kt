package com.app.myinapp.domain.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.myinapp.domain.model.Photo
import com.app.myinapp.domain.room.dao.DaoLiked

@Database(entities = [Photo::class], version = 1, exportSchema = false)
abstract class DatabaseHelper : RoomDatabase() {

    abstract fun daoLiked(): DaoLiked

    companion object {
        private var INSTANCE: DatabaseHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DatabaseHelper {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    ctx.applicationContext,
                    DatabaseHelper::class.java,
                    "wallpaper_2_database"
                ).build()

                INSTANCE = instance
                instance
            }

        }

    }

}