package repository

import com.example.domain.model.Character
import io.reactivex.Single

interface CharactersRepository {

    fun searchCharacters(title: String): Single<List<Character>>

    fun fetchCharacters(page: Int)
}