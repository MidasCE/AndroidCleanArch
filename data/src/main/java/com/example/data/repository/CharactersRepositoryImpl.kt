package com.example.data.repository

import com.example.data.api.StarwarAPI
import com.example.data.entity.toDomain
import com.example.domain.model.Character
import io.reactivex.Single
import repository.CharactersRepository

class CharactersRepositoryImpl(private val starwarAPI: StarwarAPI) :
    CharactersRepository {

    override fun searchCharacters(title: String): Single<List<Character>> =
        starwarAPI.searchCharacters(title).map { response ->
            response.results.map { dto ->
                dto.toDomain()
            }
        }

    override fun fetchCharacters(page: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
