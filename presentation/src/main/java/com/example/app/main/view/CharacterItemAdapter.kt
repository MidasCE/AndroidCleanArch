package com.example.app.main.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.main.view.model.CharacterViewEntity

class CharacterItemAdapter(private val characterItemInteractionListener: CharacterItemInteractionListener)  : RecyclerView.Adapter<CharacterItemAdapter.ViewHolder>() {
    lateinit var context: Context

    var characterList: MutableList<CharacterViewEntity> = mutableListOf()

    fun updateCharacterItem(list: List<CharacterViewEntity>) {
        characterList.clear()
        characterList.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
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

    override fun getItemCount(): Int = characterList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(context, characterList[position])
        holder.itemView.setOnClickListener {
            characterItemInteractionListener.onCharacterItemClick(characterList[position])
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val characterTitleTextView: TextView =
            itemView.findViewById(R.id.characterTitleTextView)
        private val imageView: ImageView =
            itemView.findViewById(R.id.characterImageView)

        fun bindViewHolder(context: Context, viewEntity: CharacterViewEntity) {
            initView(context, viewEntity)
        }

        private fun initView(context: Context, viewEntity: CharacterViewEntity) {
            characterTitleTextView.text = viewEntity.title
        }
    }

    interface CharacterItemInteractionListener {
        fun onCharacterItemClick(characterViewEntity : CharacterViewEntity)
    }
}