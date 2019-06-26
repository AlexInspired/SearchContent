package com.example.searchcontent.presentation.view

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchcontent.App
import com.example.searchcontent.R
import com.example.searchcontent.presentation.model.VMFilm
import com.example.searchcontent.presentation.presenter.FilmSearchPresenter
import com.example.searchcontent.presentation.view.adapter.FilmsAdapter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class FilmSearchActivity : AppCompatActivity(), FilmSearchPresenter.FilmListView {

    @Inject
    lateinit var presenter: FilmSearchPresenter
    private lateinit var adapter: FilmsAdapter

    override fun renderFilmList(vmFilmCollection: Collection<VMFilm>) {
        adapter.setFilmsCollection(vmFilmCollection)
    }

    override fun contentNotFound(b: Boolean) {
        if (b) {
            fsa_content_not_found.visibility = View.VISIBLE
            fsa_recycler_view.visibility = View.GONE
        } else {
            fsa_content_not_found.visibility = View.GONE
            fsa_recycler_view.visibility = View.VISIBLE
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        val bundle = intent.extras
        val searchPhrase = bundle.get("SearchPhrase") as String
        presenter.searchFilms(searchPhrase)
        search_et.setText(searchPhrase)
    }


    fun isNetworkAvailable(activity: Context): Boolean {
        val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.getInstance().inject(this)
        var searchET = search_et as EditText
        searchET.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                presenter.searchPhrase = s.toString()
            }

            override fun afterTextChanged(s: Editable) {
            }
        })

        searchBnt.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if(!isNetworkAvailable(this@FilmSearchActivity)) {
                    Toast.makeText(applicationContext, "Need internet connection to proceed", Toast.LENGTH_SHORT).show()
                    return
                }

                if (!presenter.searchPhrase.isEmpty())
                    presenter.searchFilms(presenter.searchPhrase)
                else {
                    Toast.makeText(applicationContext, "Type something to search", Toast.LENGTH_SHORT).show()
                }
            }
        })

        prevSearchBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(
                    this@FilmSearchActivity,
                    PreviousSearchesActivity::class.java
                ).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                this@FilmSearchActivity.startActivity(intent)
            }
        })


        val linearLayoutManager = LinearLayoutManager(this)
        fsa_recycler_view.layoutManager = linearLayoutManager
        adapter = FilmsAdapter(emptyList(), this)
        fsa_recycler_view.adapter = adapter

        presenter.setView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        this.presenter.destroy()
    }
}
