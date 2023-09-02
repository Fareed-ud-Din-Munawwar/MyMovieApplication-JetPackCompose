package com.example.mymovieapplication_jetpackcompose.data.localdatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieEntity::class], version = 1)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
//convert to MVI
//animations
//flows me change kr lo