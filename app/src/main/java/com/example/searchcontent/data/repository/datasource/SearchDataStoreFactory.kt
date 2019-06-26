package com.example.searchcontent.data.repository.datasource

import android.content.Context
import com.example.searchcontent.data.database.dao.SearchEntityDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchDataStoreFactory  @Inject constructor(val ctx: Context, val searchEntityDao : SearchEntityDao){

    fun create() : SearchDiskDataStore{
       return createSearchDiskDataStore()
    }

    fun createSearchDiskDataStore() : SearchDiskDataStore{
        return SearchDiskDataStore(searchEntityDao)
    }
}
