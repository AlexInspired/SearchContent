package com.example.searchcontent.dagger

import com.example.searchcontent.data.database.ApplicationDb
import com.example.searchcontent.presentation.presenter.FilmSearchPresenter
import com.example.searchcontent.presentation.view.FilmSearchActivity
import com.example.searchcontent.presentation.view.PreviousSearchesActivity

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, RoomModule::class, NetModule::class))
public interface AppComponent {
    fun inject(filmSearchActivity: FilmSearchActivity)
    fun inject(previousSearchesActivity: PreviousSearchesActivity)

    fun applicationDatabase(): ApplicationDb
}