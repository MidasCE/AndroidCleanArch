package com.example.domain

import com.example.domain.model.Character
import io.reactivex.Single
import repository.CharactersRepository

class CharactersInteractorImpl(val charactersRepository: CharactersRepository) : CharactersInteractor {

    override fun searchCharacters(title: String): Single<List<Character>> =
        charactersRepository.searchCharacters(title)

}
