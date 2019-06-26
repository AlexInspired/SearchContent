package com.example.searchcontent.domain.repository

import com.example.searchcontent.domain.model.Film
import io.reactivex.Observable

interface FilmRepository {
    fun searchFilms (searchWords : String): Observable<List<Film>>
}