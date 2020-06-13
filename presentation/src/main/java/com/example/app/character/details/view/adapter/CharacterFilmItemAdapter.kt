package com.example.app.character.details.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.character.details.view.model.FilmViewEntity

class CharacterFilmItemAdapter :
    RecyclerView.Adapter<CharacterFilmItemAdapter.ViewHolder>() {

    private var filmList: MutableList<FilmViewEntity> = mutableListOf()

    fun updateCharacterItem(list: List<FilmViewEntity>) {
        filmList.clear()
        filmList.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemLayoutView = LayoutInflater.from(parent.context)
            .inflate(R.layout.film_item, null)
        itemLayoutView.layoutParams = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        return ViewHolder(
            itemLayoutView
        )
    }

    override fun getItemCount(): Int = filmList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(filmList[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView =
            itemView.findViewById(R.id.filmTitleTextView)
        private val subTitleTextView: TextView =
            itemView.findViewById(R.id.filmSubTitleTextView)

        fun bindViewHolder(viewEntity: FilmViewEntity) {
            initView(viewEntity)
        }

        private fun initView(viewEntity: FilmViewEntity) {
            titleTextView.text = viewEntity.title
            subTitleTextView.text = viewEntity.openingCrawl
        }
    }
}
