package com.example.searchcontent.domain.interactor

import com.example.searchcontent.domain.executor.PostExecutionThread
import com.example.searchcontent.domain.executor.ThreadExecutor
import com.example.searchcontent.domain.repository.SearchRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetPreviousSearchesUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : UseCase<List<String>, Unit>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Unit): Observable<List<String>> {
        return this.searchRepository.getSearches()
    }
}