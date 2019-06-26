package com.example.searchcontent

import com.example.searchcontent.domain.interactor.SearchContentUseCase
import com.example.searchcontent.presentation.mapper.VMFilmDataMapper
import com.example.searchcontent.presentation.presenter.FilmSearchPresenter
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class FilmSearchPresenterUnitTest {

    lateinit var presenter: FilmSearchPresenter
    var view: FilmSearchPresenter.FilmListView = mock()
    var useCase: SearchContentUseCase = mock()
    val vmFilmDataMapper: VMFilmDataMapper = mock()

    @Before
    fun setup() {
        presenter = FilmSearchPresenter(useCase, vmFilmDataMapper)
        presenter.setView(view)
    }

    @Test
    fun testErrorHandling() {
        presenter.handleServerError(Throwable())
        verify(view).contentNotFound(true)
    }

}
