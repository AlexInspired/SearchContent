package com.example.searchcontent.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchcontent.App
import com.example.searchcontent.R
import com.example.searchcontent.presentation.presenter.PreviousSearchPresenter
import com.example.searchcontent.presentation.view.adapter.PreviousSearchesAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_previous_searches.*
import javax.inject.Inject

class PreviousSearchesActivity : AppCompatActivity(), PreviousSearchPresenter.SearchPhraseListView {

    @Inject
    lateinit var presenter: PreviousSearchPresenter
    private lateinit var adapter: PreviousSearchesAdapter

    override fun renderSearchPhraseList(list: List<String>) {
        adapter.setPreviousSearchesCollection(list)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_previous_searches)
        App.getInstance().inject(this)
        presenter.setView(this)
        val linearLayoutManager = LinearLayoutManager(this)
        psa_recycler_view.layoutManager = linearLayoutManager
        adapter = PreviousSearchesAdapter(emptyList(), this)
        psa_recycler_view.adapter = adapter

        presenter.getPreviousSearches()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.presenter.destroy()
    }
}