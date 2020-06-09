package com.example.app.main.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.main.view.model.CharacterItemViewEntity

class CharacterItemAdapter  : RecyclerView.Adapter<CharacterItemAdapter.ViewHolder>() {
    var characterItemList: MutableList<CharacterItemViewEntity> = mutableListOf()

    fun updateCharacterItem(list: List<CharacterItemViewEntity>) {
        if (characterItemList.isEmpty()) {
            characterItemList.addAll(list)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemLayoutView = LayoutInflater.from(parent.context)
            .inflate(R.layout.character_item, null)
        itemLayoutView.layoutParams = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        return ViewHolder(
            itemLayoutView
        )
    }

    override fun getItemCount(): Int = characterItemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(characterItemList[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val characterTitleTextView: TextView =
            itemView.findViewById(R.id.characterTitleTextView)

        fun bindViewHolder(viewEntity: CharacterItemViewEntity) {
            initView(viewEntity)
        }

        private fun initView(viewEntity: CharacterItemViewEntity) {
            characterTitleTextView.text = viewEntity.title
        }
    }

}