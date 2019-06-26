package com.example.searchcontent.data.repository.datasource

import com.example.searchcontent.data.entity.FilmEntity
import com.example.searchcontent.data.net.ApiConstants
import com.example.searchcontent.data.net.ApiInterface
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FilmCloudDataStore(var apiInterface: ApiInterface) : FilmDataStore{

    override fun searchFilms(searchPhrase: String): Observable<List<FilmEntity>> {

        var s = apiInterface.searchFilms(ApiConstants.QUERY_PARAM_API_KEY, searchPhrase)
        return s.map { search -> search.list  }

    }

}

data class SearchNestedJsonArray (
    @SerializedName("Search") var list: List<FilmEntity>
)