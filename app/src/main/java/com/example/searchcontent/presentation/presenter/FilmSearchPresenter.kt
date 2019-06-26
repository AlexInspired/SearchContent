package com.example.searchcontent.presentation.presenter

import com.example.searchcontent.domain.interactor.SearchContentUseCase
import com.example.searchcontent.domain.model.Film
import com.example.searchcontent.presentation.mapper.VMFilmDataMapper
import com.example.searchcontent.presentation.model.VMFilm
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class FilmSearchPresenter @Inject constructor(
    private val searchContentUseCase: SearchContentUseCase,
    private val vmFilmDataMapper: VMFilmDataMapper
) : Presenter {

    interface FilmListView {
        fun renderFilmList(vmFilmCollection: Collection<VMFilm>)
        fun contentNotFound(b: Boolean)
    }

    var searchPhrase: String = ""

    var filmListView: FilmSearchPresenter.FilmListView? = null

    fun setView(filmListView: FilmListView) {
        this.filmListView = filmListView
    }

    fun searchFilms(searchPhrase: String) {
        this.searchContentUseCase.execute(FilmListObserver(), searchPhrase)
    }

    override fun resume() {}

    override fun pause() {}

    override fun destroy() {
        this.searchContentUseCase.dispose()
        this.filmListView = null
    }


    private fun showFilmCollectionInView(filmCollection: Collection<Film>) {
        val vmFilmCollection = this.vmFilmDataMapper.transform(filmCollection)
        this.filmListView!!.renderFilmList(vmFilmCollection)
    }

    inner class FilmListObserver : DisposableObserver<List<Film>>() {
        override fun onComplete() {
            this@FilmSearchPresenter.filmListView!!.contentNotFound(false)
        }

        override fun onNext(t: List<Film>) {
            this@FilmSearchPresenter.showFilmCollectionInView(t)
        }

        override fun onError(e: Throwable) {
            this@FilmSearchPresenter.handleServerError(e)
        }
    }

    fun handleServerError(e: Throwable){
        this@FilmSearchPresenter.filmListView!!.contentNotFound(true)
    }
}