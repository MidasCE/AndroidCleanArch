package com.example.app.character.details.view.presenter

import com.example.app.main.view.model.CharacterViewEntity

interface CharacterDetailsScreenPresenter {

    fun updateCharacterDetails(characterViewEntity: CharacterViewEntity)

    fun currentCharacterViewEntity(): CharacterViewEntity?
}
