package com.example.searchcontent.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class SearchEntity(
    @PrimaryKey val id: String,
    val searchPhrase: String
    )

