package com.example.app.character.details.view.presenter

import com.example.app.main.view.model.CharacterViewEntity

class CharacterDetailsScreenPresenterImpl : CharacterDetailsScreenPresenter {

    //TODO currently, now only using memory cache to saved selected characters details.
    @Volatile
    private var characterViewEntity: CharacterViewEntity? = null

    @Synchronized
    override fun updateCharacterDetails(characterViewEntity: CharacterViewEntity) {
        this.characterViewEntity = characterViewEntity
    }

    override fun currentCharacterViewEntity(): CharacterViewEntity? = characterViewEntity
}
