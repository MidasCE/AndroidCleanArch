package com.example.domain


import com.example.domain.model.Character
import com.example.domain.model.Film
import com.example.domain.model.HomeWorld
import com.example.domain.model.Specie
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import repository.CharactersRepository

@RunWith(MockitoJUnitRunner::class)
class CharactersInteractorImplTest {

    @Mock
    lateinit var repository: CharactersRepository

    private lateinit var charactersInteractor : CharactersInteractor

    @Before
    fun setUp() {
        charactersInteractor = CharactersInteractorImpl(repository)
    }

    @Test
    fun `searchCharacters`() {
        val character = Character(
            "name",
            "birthYear",
            "height",
            emptyList(),
            emptyList(),
            "url"
        )

        whenever(repository.searchCharacters("name")).thenReturn(Single.just(listOf(character)))

        charactersInteractor.searchCharacters("name").test()
            .assertValue(listOf(character))
    }

    @Test
    fun `fetchCharacters`() {
        val character = Character(
            "name",
            "birthYear",
            "height",
            emptyList(),
            emptyList(),
            "url"
        )

        whenever(repository.fetchCharacters(1)).thenReturn(Single.just(listOf(character)))

        charactersInteractor.fetchCharacters(1).test()
            .assertValue(listOf(character))
    }

    @Test
    fun `Test getHomeWorld`() {
        val homeWorld = HomeWorld(
            "name",
            "population"
        )

        whenever(repository.getHomeWorld("url")).thenReturn(Single.just(homeWorld))

        charactersInteractor.getHomeWorld("url").test()
            .assertValue(homeWorld)
    }

    @Test
    fun `Test getSpecies`() {
        val specie = Specie(
            "name",
            "homeWorldUrl",
            "population"
        )
        whenever(repository.getSpecie("url")).thenReturn(Single.just(specie))

        charactersInteractor.getSpecie("url").test()
            .assertValue(specie)
    }

    @Test
    fun `Test film`() {
        val film = Film(
            "name",
            "crawl"
        )
        whenever(repository.getCharacterFilm("url")).thenReturn(Single.just(film))

        charactersInteractor.getCharacterFilm("url").test()
            .assertValue(film)

    }
}
