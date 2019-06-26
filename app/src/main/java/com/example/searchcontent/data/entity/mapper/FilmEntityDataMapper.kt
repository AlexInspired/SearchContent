package com.example.searchcontent.data.entity.mapper

import com.example.searchcontent.data.entity.FilmEntity
import com.example.searchcontent.domain.model.Film
import java.util.ArrayList
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FilmEntityDataMapper @Inject constructor(){

    fun transform(filmEntity: FilmEntity): Film {
        return Film(filmEntity.title, filmEntity.year, filmEntity.imdbID, filmEntity.type, filmEntity.poster)
    }

    fun transform(filmEntityCollection: Collection<FilmEntity>): List<Film> {
        val userList = ArrayList<Film>(20)
        for (userEntity in filmEntityCollection) {
            val user = transform(userEntity)
            if (user != null) {
                userList.add(user)
            }
        }
        return userList
    }
}