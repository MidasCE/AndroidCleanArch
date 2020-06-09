package com.example.app.main.view.presenter

import com.example.app.main.view.model.CharacterItemViewEntity

interface CharacterListScreenPresenter {

    fun fetchCharacterList()

    fun searchCharacters(title : String)

}
