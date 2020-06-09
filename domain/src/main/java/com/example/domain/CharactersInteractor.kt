package com.example.domain

import com.example.domain.model.Character
import io.reactivex.Single

interface CharactersInteractor {

    fun searchCharacters(title: String) : Single<List<Character>>

    fun fetchCharacters(page: Int) : Single<List<Character>>

}
