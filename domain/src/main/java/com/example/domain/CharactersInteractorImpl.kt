package com.example.domain

import com.example.domain.model.Character
import com.example.domain.model.Film
import com.example.domain.model.HomeWorld
import com.example.domain.model.Specie
import io.reactivex.Single
import repository.CharactersRepository

class CharactersInteractorImpl(private val charactersRepository: CharactersRepository) : CharactersInteractor {

    override fun searchCharacters(title: String): Single<List<Character>> =
        charactersRepository.searchCharacters(title)

    override fun fetchCharacters(page: Int): Single<List<Character>> =
        charactersRepository.fetchCharacters(page)


    override fun getHomeWorld(url: String): Single<HomeWorld> =
        charactersRepository.getHomeWorld(url)

    override fun getSpecies(url: String): Single<Specie> =
        charactersRepository.getSpecies(url)

    override fun getCharacterFilm(url: String): Single<Film> =
        charactersRepository.getCharacterFilms(url)
}
