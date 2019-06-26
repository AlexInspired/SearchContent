package com.example.searchcontent.data.repository.datasource

import android.content.Context
import com.example.searchcontent.data.net.ApiInterface
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FilmDataStoreFactory @Inject constructor( val ctx: Context, val apiInterface : ApiInterface){

    fun create(): FilmDataStore{
        return createCloudDataStore()
    }

    fun createCloudDataStore(): FilmDataStore {
        return FilmCloudDataStore(apiInterface)
    }

}