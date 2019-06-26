package com.example.searchcontent.domain.repository

import io.reactivex.Observable

interface SearchRepository{
    fun putSearch(searchPhrase: String)
    fun getSearches() : Observable<List<String>>
}