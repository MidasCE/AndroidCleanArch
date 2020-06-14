package com.example.domain

import com.example.domain.model.Character
import com.example.domain.model.Film
import com.example.domain.model.HomeWorld
import com.example.domain.model.Specie
import io.reactivex.Single

interface CharactersInteractor {

    fun searchCharacters(title: String) : Single<List<Character>>

    fun fetchCharacters(page: Int) : Single<List<Character>>

    fun getHomeWorld(url: String): Single<HomeWorld>

    fun getSpecie(url: String): Single<Specie>

    fun getCharacterFilm(url: String): Single<Film>

}
