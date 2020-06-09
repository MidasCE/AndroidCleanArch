package com.example.app.main.view

import com.example.app.main.view.model.CharacterItemViewEntity

interface CharacterListScreenView {
    fun updateCharacterList(list: List<CharacterItemViewEntity>)

    fun showLoading()

    fun hideLoading()

    fun showError()
}
