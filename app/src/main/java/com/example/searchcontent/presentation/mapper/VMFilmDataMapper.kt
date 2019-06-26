package com.example.searchcontent.presentation.mapper

import com.example.searchcontent.domain.model.Film
import com.example.searchcontent.presentation.model.VMFilm
import java.util.ArrayList
import javax.inject.Inject

open class VMFilmDataMapper @Inject constructor(){

    fun transform(film: Film): VMFilm {
        return VMFilm(film.title, film.year, film.imdbID, film.type, film.poster)
    }

    fun transform(filmCollection: Collection<Film>): Collection<VMFilm> {
        var vmFilmCollection = ArrayList<VMFilm>()
        if (!filmCollection.isEmpty()) {
            for (user in filmCollection) {
                vmFilmCollection.add(transform(user))
            }
        } else {
            vmFilmCollection = emptyList<VMFilm>() as ArrayList<VMFilm>
        }
        return vmFilmCollection
    }
}