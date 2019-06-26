package com.example.searchcontent.data.repository

import com.example.searchcontent.data.repository.datasource.SearchDataStoreFactory
import com.example.searchcontent.domain.repository.SearchRepository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchDataRepository @Inject constructor(val searchDataStoreFactory: SearchDataStoreFactory) : SearchRepository{
    override fun putSearch(searchPhrase: String) {
        searchDataStoreFactory.create().putSearch(searchPhrase)
    }

    override fun getSearches(): Observable<List<String>> {
        return searchDataStoreFactory.create().getSearches()
    }

}