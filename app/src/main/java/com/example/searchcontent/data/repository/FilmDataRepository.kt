package com.example.searchcontent.data.repository

import com.example.searchcontent.data.entity.mapper.FilmEntityDataMapper
import com.example.searchcontent.data.repository.datasource.FilmDataStoreFactory
import com.example.searchcontent.domain.model.Film
import com.example.searchcontent.domain.repository.FilmRepository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FilmDataRepository @Inject constructor(val filmDataStoreFactory: FilmDataStoreFactory, private val filmEntityDataMapper: FilmEntityDataMapper) : FilmRepository{

    override fun searchFilms(searchPhrase: String): Observable<List<Film>> {
        val filmDataStore = filmDataStoreFactory.create()
        return filmDataStore.searchFilms(searchPhrase).map{filmEntityDataMapper.transform(it)}
    }

}