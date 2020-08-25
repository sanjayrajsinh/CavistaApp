package com.cavista.cavistaapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cavista.cavistaapp.webservice.ApiConstant.DATABASE_NAME

@Database(entities = [(ImageComment::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {
        abstract fun imageDao(): ImageDao
        companion object {

                @Volatile
                private var INSTANCE: AppDatabase? = null

                fun getInstance(context: Context): AppDatabase {
                        return INSTANCE ?: synchronized(this) {
                                createDatabase(context).also {
                                        INSTANCE = it
                                }
                        }
                }

                private fun createDatabase(context: Context): AppDatabase {
                        return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DATABASE_NAME)
                                .fallbackToDestructiveMigration()
                                .allowMainThreadQueries()
                                .build()
                }
        }
}