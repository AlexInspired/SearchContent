package com.example.searchcontent.data.repository.datasource

import com.example.searchcontent.data.entity.SearchEntity
import io.reactivex.Observable

interface SearchDataStore{
    fun putSearch(searchPhrase: String)
    fun getSearches() : Observable<List<String>>
}