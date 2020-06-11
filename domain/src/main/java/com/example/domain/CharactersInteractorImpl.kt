package com.example.domain

import com.example.domain.model.Character
import io.reactivex.Single
import repository.CharactersRepository

class CharactersInteractorImpl(private val charactersRepository: CharactersRepository) : CharactersInteractor {


    override fun searchCharacters(title: String): Single<List<Character>> =
        charactersRepository.searchCharacters(title)

    override fun fetchCharacters(page: Int): Single<List<Character>> =
        charactersRepository.fetchCharacters(page)

}
