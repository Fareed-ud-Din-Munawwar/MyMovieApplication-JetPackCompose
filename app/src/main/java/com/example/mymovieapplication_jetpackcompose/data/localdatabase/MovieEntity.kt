package com.example.mymovieapplication_jetpackcompose.data.localdatabase

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mymovieapplication_jetpackcompose.domain.entities.Movie

@Entity
data class MovieEntity(
    @PrimaryKey
    val id: Int = 0,
    @Embedded val movie: Movie = Movie()
)