package com.example.searchcontent.presentation.presenter

import com.example.searchcontent.domain.interactor.GetPreviousSearchesUseCase
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class PreviousSearchPresenter @Inject constructor(private val useCase: GetPreviousSearchesUseCase) : Presenter {

    interface SearchPhraseListView {
        fun renderSearchPhraseList(list: List<String>)
    }

    var searchPhraseListView: PreviousSearchPresenter.SearchPhraseListView? = null

    fun setView(searchPhraseListView: SearchPhraseListView) {
        this.searchPhraseListView = searchPhraseListView
    }

    fun getPreviousSearches() {
        this.useCase.execute(PreviousSearchesObserver(), Unit)
    }

    override fun resume() {}

    override fun pause() {}

    override fun destroy() {
        this.useCase.dispose()
        this.searchPhraseListView = null
    }

    inner class PreviousSearchesObserver : DisposableObserver<List<String>>() {
        override fun onComplete() {}
        override fun onError(e: Throwable) {}
        override fun onNext(t: List<String>) {
            this@PreviousSearchPresenter.showPreviousSearchesInView(t)
        }
    }

    private fun showPreviousSearchesInView(list : List<String>){
        this.searchPhraseListView!!.renderSearchPhraseList(list.distinct())
    }
}