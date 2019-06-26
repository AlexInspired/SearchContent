package com.example.searchcontent.domain.interactor

import com.example.searchcontent.domain.executor.PostExecutionThread
import com.example.searchcontent.domain.executor.ThreadExecutor
import com.example.searchcontent.domain.model.Film
import com.example.searchcontent.domain.repository.FilmRepository
import com.example.searchcontent.domain.repository.SearchRepository
import io.reactivex.Observable
import javax.inject.Inject

open class SearchContentUseCase @Inject constructor(private val filmRepository: FilmRepository, private val searchRepository: SearchRepository, threadExecutor: ThreadExecutor,
                                               postExecutionThread : PostExecutionThread)
    : UseCase<List<Film>, String>(threadExecutor, postExecutionThread){

    override fun buildUseCaseObservable(params: String): Observable<List<Film>> {
        this.searchRepository.putSearch(params)
        this.searchRepository.getSearches()
        return this.filmRepository.searchFilms(params)
    }
}