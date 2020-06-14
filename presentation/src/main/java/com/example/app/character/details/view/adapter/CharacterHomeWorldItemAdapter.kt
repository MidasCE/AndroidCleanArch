package com.example.app.character.details.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.character.details.view.model.CharacterHomeWorldViewEntity

class CharacterHomeWorldItemAdapter :
    RecyclerView.Adapter<CharacterHomeWorldItemAdapter.ViewHolder>() {
    lateinit var context: Context

    private var homeWorldList: MutableList<CharacterHomeWorldViewEntity> = mutableListOf()

    fun updateHomeWorldItem(list: List<CharacterHomeWorldViewEntity>) {
        homeWorldList.clear()
        homeWorldList.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val itemLayoutView = LayoutInflater.from(parent.context)
            .inflate(R.layout.homeworld_item, null)
        itemLayoutView.layoutParams = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        return ViewHolder(
            itemLayoutView
        )
    }

    override fun getItemCount(): Int = homeWorldList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(homeWorldList[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val specieTitleTextView: TextView =
            itemView.findViewById(R.id.specieTitleTextView)
        private val languageTitleTextView: TextView =
            itemView.findViewById(R.id.languageTitleTextView)
        private val homeWorldTitleTextView: TextView =
            itemView.findViewById(R.id.homeWorldTitleTextView)
        private val populationTextView: TextView =
            itemView.findViewById(R.id.populationTextView)

        fun bindViewHolder(viewEntity: CharacterHomeWorldViewEntity) {
            initView(viewEntity)
        }

        private fun initView(viewEntity: CharacterHomeWorldViewEntity) {
            specieTitleTextView.text = context.getString(R.string.character_home_world_species_title, viewEntity.specieName)
            languageTitleTextView.text = context.getString(R.string.character_home_world_language, viewEntity.language)
            homeWorldTitleTextView.visibility = viewEntity.homeWorldName?.let { homeWorldName ->
                homeWorldTitleTextView.text = context.getString(R.string.character_home_world_title, homeWorldName)
                VISIBLE
            } ?: GONE
            populationTextView.visibility = viewEntity.homeWorldPopulation?.let { homeWorldPopulation ->
                populationTextView.text = context.getString(R.string.character_home_world_population, homeWorldPopulation)
                VISIBLE
            } ?: GONE
        }
    }
}
