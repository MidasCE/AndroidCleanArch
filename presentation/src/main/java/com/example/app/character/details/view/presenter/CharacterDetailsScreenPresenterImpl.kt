package com.example.app.character.details.view.presenter

import com.example.app.main.view.model.CharacterViewEntity

class CharacterDetailsScreenPresenterImpl : CharacterDetailsScreenPresenter {

    @Volatile
    var characterViewEntity: CharacterViewEntity? = null

    @Synchronized
    override fun updateCharacterDetails(characterViewEntity: CharacterViewEntity) {
        this.characterViewEntity = characterViewEntity
    }

    override fun currentCharacterViewEntity(): CharacterViewEntity? = characterViewEntity
}
