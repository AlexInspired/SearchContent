package com.example.searchcontent.data.entity

import com.google.gson.annotations.SerializedName
import androidx.room.PrimaryKey
import androidx.room.Entity

@Entity(tableName = "film_entity")
data class FilmEntity (
    @PrimaryKey @SerializedName("Id") val id: String,
    @SerializedName("Title") val title : String,
    @SerializedName("Year") val year : String,
    @SerializedName("imdbID") val imdbID : String,
    @SerializedName("Type") val type : String,
    @SerializedName("Poster") val poster : String
)