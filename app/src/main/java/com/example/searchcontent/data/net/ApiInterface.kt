package com.example.searchcontent.data.net

import com.example.searchcontent.data.repository.datasource.SearchNestedJsonArray
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {


    @GET("http://www.omdbapi.com/?")
    fun searchFilms(
        @Query("apikey") apiKey: String,
        @Query("s") queryParam: String
    )
            : Observable<SearchNestedJsonArray>

}