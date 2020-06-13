package repository

import com.example.domain.model.Character
import com.example.domain.model.Film
import com.example.domain.model.HomeWorld
import com.example.domain.model.Specie
import io.reactivex.Single

interface CharactersRepository {

    fun searchCharacters(title: String): Single<List<Character>>

    fun fetchCharacters(page: Int): Single<List<Character>>

    fun getHomeWorld(url: String): Single<HomeWorld>

    fun getSpecies(url: String): Single<Specie>

    fun getCharacterFilms(url: String): Single<Film>
}