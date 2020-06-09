package com.example.app.main.view.presenter

import com.example.app.main.view.model.CharacterItemViewEntity

interface CharacterListScreenPresenter {

    fun fetchCharacterList() : List<CharacterItemViewEntity>

    fun searchCharacters(title : String) : List<CharacterItemViewEntity>

}
