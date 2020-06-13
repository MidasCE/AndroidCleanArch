package com.example.app.character.details.view

import com.example.app.character.details.view.model.CharacterHomeWorldViewEntity
import com.example.app.character.details.view.model.FilmViewEntity

interface CharacterDetailsView {

    fun updateCharacterBackgroundDetails(viewEntityList: List<CharacterHomeWorldViewEntity>)

    fun updateCharacterFilmDetails(filmViewEntityList: List<FilmViewEntity>)
}
