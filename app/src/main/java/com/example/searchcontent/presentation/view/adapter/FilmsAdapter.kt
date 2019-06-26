package com.example.searchcontent.presentation.view.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.searchcontent.inflate
import com.example.searchcontent.presentation.model.VMFilm
import kotlinx.android.synthetic.main.fsa_recycler_view_item_row.view.*


class FilmsAdapter(private var vmFilms: List<VMFilm>, var context: Context) :
    RecyclerView.Adapter<FilmsAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsAdapter.ItemHolder {
        val inflatedView = parent.inflate(com.example.searchcontent.R.layout.fsa_recycler_view_item_row, false)
        return ItemHolder(inflatedView, context)
    }

    override fun getItemCount() = vmFilms.size

    override fun onBindViewHolder(holder: FilmsAdapter.ItemHolder, position: Int) {
        val itemFilm = vmFilms[position]
        holder.bindItem(itemFilm)
    }

    fun setFilmsCollection(filmsCollection: Collection<VMFilm>) {
        this.vmFilms = filmsCollection as List<VMFilm>
        this.notifyDataSetChanged()
    }


    class ItemHolder(v: View, context: Context) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        private var vmFilm: VMFilm? = null
        var poster: ImageView = view.film_poster
        private var context: Context = context

        fun bindItem(vmFilm: VMFilm) {
            this.vmFilm = vmFilm
            view.itemDescription.text = vmFilm.title + " " + vmFilm.year + " " + vmFilm.type + " "
            val imageView = poster as ImageView
            Glide.with(context).load(vmFilm.poster).into(imageView);
        }
    }
}