package com.example.data.repository

import com.example.data.api.StarwarAPI
import com.example.data.response.toDomain
import com.example.domain.model.Character
import com.example.domain.model.Film
import com.example.domain.model.HomeWorld
import com.example.domain.model.Specie
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

    override fun fetchCharacters(page: Int): Single<List<Character>> =
        starwarAPI.fetchCharacters(page).map { response ->
            response.results.map { dto ->
                dto.toDomain()
            }
        }

    override fun getHomeWorld(url: String): Single<HomeWorld> =
        starwarAPI.getHomeWorld(url).map { response ->
            response.toDomain()
        }

    override fun getSpecie(url: String): Single<Specie> =
        starwarAPI.getSpecies(url).map { response ->
            response.toDomain()
        }

    override fun getCharacterFilm(url: String): Single<Film> =
        starwarAPI.getCharacterFilms(url).map { response ->
            response.toDomain()
        }
}
