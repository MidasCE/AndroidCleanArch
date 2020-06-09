package com.example.app.main.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app.R
import com.example.app.main.view.model.CharacterItemViewEntity

class CharacterItemAdapter  : RecyclerView.Adapter<CharacterItemAdapter.ViewHolder>() {
    lateinit var context: Context

    var characterItemList: MutableList<CharacterItemViewEntity> = mutableListOf()

    fun updateCharacterItem(list: List<CharacterItemViewEntity>) {
        characterItemList.clear()
        characterItemList.addAll(list)
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

    override fun getItemCount(): Int = characterItemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(context, characterItemList[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val characterTitleTextView: TextView =
            itemView.findViewById(R.id.characterTitleTextView)
        private val imageView: ImageView =
            itemView.findViewById(R.id.characterImageView)

        fun bindViewHolder(context: Context, viewEntity: CharacterItemViewEntity) {
            initView(context, viewEntity)
        }

        private fun initView(context: Context, viewEntity: CharacterItemViewEntity) {
            characterTitleTextView.text = viewEntity.title
        }
    }

}