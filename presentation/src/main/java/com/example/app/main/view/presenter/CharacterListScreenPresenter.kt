package com.example.app.main.view.presenter

import com.example.app.main.view.model.CharacterViewEntity

interface CharacterListScreenPresenter {

    fun fetchCharacterList()

    fun searchCharacters(title : String)

}
