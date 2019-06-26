package com.example.searchcontent.dagger

import android.content.Context
import androidx.room.Room
import com.example.searchcontent.data.database.ApplicationDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import com.example.searchcontent.data.database.dao.SearchEntityDao
import com.example.searchcontent.data.repository.FilmDataRepository
import com.example.searchcontent.data.repository.SearchDataRepository
import com.example.searchcontent.domain.repository.FilmRepository
import com.example.searchcontent.domain.repository.SearchRepository


@Module
class RoomModule{

    lateinit  var searchDatabase: ApplicationDb

    @Provides
    @Singleton
    fun provideApplicationDb(context: Context) : ApplicationDb{
        searchDatabase =  Room.databaseBuilder(context, ApplicationDb::class.java, "search_content_db")
            .allowMainThreadQueries().build() as ApplicationDb
        return searchDatabase;
    }

    @Provides
    @Singleton
    fun provideSearchEntityDao(applicationDb: ApplicationDb): SearchEntityDao {
        return searchDatabase!!.getSearchEntityDao()
    }

    @Provides
    @Singleton
    fun provideFilmRepository(filmDataRepository: FilmDataRepository): FilmRepository {
        return filmDataRepository
    }

    @Provides
    @Singleton
    fun provideSearchRepository(searchDataRepository: SearchDataRepository): SearchRepository {
        return searchDataRepository
    }
}