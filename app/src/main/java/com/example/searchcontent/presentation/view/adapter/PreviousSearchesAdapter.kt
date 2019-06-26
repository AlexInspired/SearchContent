package com.example.searchcontent.presentation.view.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.example.searchcontent.inflate
import com.example.searchcontent.presentation.view.FilmSearchActivity
import kotlinx.android.synthetic.main.psa_recycler_view_item_row.view.*

class PreviousSearchesAdapter(private var searchPhrases : List<String>, var context : Context) : RecyclerView.Adapter<PreviousSearchesAdapter.ItemHolder>() {

    override fun getItemCount() = searchPhrases.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreviousSearchesAdapter.ItemHolder {
        val inflatedView = parent.inflate(com.example.searchcontent.R.layout.psa_recycler_view_item_row, false)
        return ItemHolder(inflatedView, context)
    }

    override fun onBindViewHolder(holder: PreviousSearchesAdapter.ItemHolder, position: Int) {
        val item = searchPhrases[position]
        holder.bindItem(item)

    }

    fun setPreviousSearchesCollection(list: List<String>) {
        this.searchPhrases = list
        this.notifyDataSetChanged()
    }

    class ItemHolder(v: View, context: Context) : RecyclerView.ViewHolder(v), View.OnClickListener{
        private var view : View = v
        private var context : Context  = context
        lateinit private var searchPhrase : String
        init{v.setOnClickListener(this)}

        override fun onClick(v: View){
            val intent = Intent(context, FilmSearchActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            intent.putExtra("SearchPhrase", searchPhrase)
            context.startActivity(intent)
        }

        fun bindItem(searchPhrase: String){
            view.searchItemDescription.text = searchPhrase
            this.searchPhrase = searchPhrase
        }

    }
}