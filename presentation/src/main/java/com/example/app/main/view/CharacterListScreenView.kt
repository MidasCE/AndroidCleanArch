package com.example.app.main.view

import com.example.app.main.view.model.CharacterViewEntity

interface CharacterListScreenView {

    fun addCharacterList(list: List<CharacterViewEntity>)

    fun updateCharacterList(list: List<CharacterViewEntity>)

    fun showLoading()

    fun hideLoading()

    fun showError()
}
