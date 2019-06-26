package com.example.searchcontent.data.database.dao

import androidx.room.*
import com.example.searchcontent.data.entity.SearchEntity
import io.reactivex.Observable

@Dao
interface SearchEntityDao {
    @Query("SELECT * FROM SearchEntity ")
    fun searches(): Observable<List<SearchEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(searchEntity: SearchEntity)
}
