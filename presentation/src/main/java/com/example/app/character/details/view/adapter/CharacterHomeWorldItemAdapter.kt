package com.example.app.character.details.view.adapter

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

    private var homeWorldList: MutableList<CharacterHomeWorldViewEntity> = mutableListOf()

    fun updateHomeWorldItem(list: List<CharacterHomeWorldViewEntity>) {
        homeWorldList.clear()
        homeWorldList.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
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
            specieTitleTextView.text = viewEntity.specieName
            languageTitleTextView.text = viewEntity.language
            homeWorldTitleTextView.visibility = viewEntity.homeWorldName?.let { homeWorldName ->
                homeWorldTitleTextView.text = homeWorldName
                VISIBLE
            } ?: GONE
            populationTextView.visibility = viewEntity.homeWorldPopulation?.let { homeWorldPopulation ->
                populationTextView.text = homeWorldPopulation
                VISIBLE
            } ?: GONE
        }
    }
}
