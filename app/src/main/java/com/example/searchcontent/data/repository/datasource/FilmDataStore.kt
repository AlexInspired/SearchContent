package com.example.searchcontent.data.repository.datasource

import com.example.searchcontent.data.entity.FilmEntity
import io.reactivex.Observable

interface FilmDataStore{
    fun searchFilms(searchPhrase: String): Observable<List<FilmEntity>>
}