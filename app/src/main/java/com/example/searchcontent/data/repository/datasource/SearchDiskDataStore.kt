package com.example.searchcontent.data.repository.datasource

import android.os.SystemClock
import com.example.searchcontent.data.database.dao.SearchEntityDao
import com.example.searchcontent.data.entity.SearchEntity
import io.reactivex.Observable

class SearchDiskDataStore(var searchEntityDao: SearchEntityDao) : SearchDataStore {

    override fun getSearches(): Observable<List<String>> {
        return searchEntityDao.searches().map { list -> list.map { item -> item.searchPhrase } }
    }

    override fun putSearch(searchPhrase: String) {
        searchEntityDao.insert(SearchEntity(SystemClock.currentThreadTimeMillis().toString(), searchPhrase))
    }

}