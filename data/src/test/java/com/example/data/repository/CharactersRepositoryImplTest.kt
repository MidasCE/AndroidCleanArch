package com.example.data.repository

import com.example.data.api.StarwarAPI
import com.example.data.response.*
import com.example.domain.model.Character
import com.example.domain.model.Film
import com.example.domain.model.HomeWorld
import com.example.domain.model.Specie
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CharactersRepositoryImplTest {

    @Mock
    lateinit var api: StarwarAPI

    private lateinit var charactersRepositoryImpl : CharactersRepositoryImpl

    @Before
    fun setUp() {
        charactersRepositoryImpl = CharactersRepositoryImpl(api)
    }

    @Test
    fun `Test searchCharacters`() {
        val characterDto = CharacterDto(
            "name",
            "height",
            "birthYear",
            listOf("url"),
            listOf("url"),
            "url"
        )

        val character = Character(
            "name",
            "height",
            "birthYear",
            listOf("url"),
            listOf("url"),
            "url")
        val response = CharactersResponse(listOf(characterDto))
        val testObserver = TestObserver<List<Character>>()
        whenever(api.searchCharacters("name")).thenReturn(
            Single.just(response))

        charactersRepositoryImpl.searchCharacters("name").subscribe(testObserver)

        testObserver.awaitTerminalEvent()
        testObserver.assertValue(listOf(character))
        verify(api).searchCharacters("name")
    }

    @Test
    fun `Test fetchCharacters`() {
        val characterDto = CharacterDto(
            "name",
            "height",
            "birthYear",
            listOf("url"),
            listOf("url"),
            "url"
        )

        val character = Character(
            "name",
            "height",
            "birthYear",
            listOf("url"),
            listOf("url"),
            "url")
        val response = CharactersResponse(listOf(characterDto))
        val testObserver = TestObserver<List<Character>>()
        whenever(api.fetchCharacters(1)).thenReturn(
            Single.just(response))

        charactersRepositoryImpl.fetchCharacters(1).subscribe(testObserver)

        testObserver.awaitTerminalEvent()
        testObserver.assertValue(listOf(character))
        verify(api).fetchCharacters(1)
    }

    @Test
    fun `Test getHomeWorld`() {
        val response = HomeWorldResponse(
            "name",
            "population"
        )

        val homeWorld = HomeWorld(
            "name",
            "population"
        )

        val testObserver = TestObserver<HomeWorld>()
        whenever(api.getHomeWorld("url")).thenReturn(
            Single.just(response))

        charactersRepositoryImpl.getHomeWorld("url").subscribe(testObserver)

        testObserver.awaitTerminalEvent()
        testObserver.assertValue(homeWorld)
        verify(api).getHomeWorld("url")
    }

    @Test
    fun `Test getSpecies`() {
        val response = SpecieResponse(
            "name",
            "homeWorldUrl",
            "population"
        )

        val specie = Specie(
            "name",
            "homeWorldUrl",
            "population"
        )

        val testObserver = TestObserver<Specie>()
        whenever(api.getSpecies("url")).thenReturn(
            Single.just(response))

        charactersRepositoryImpl.getSpecie("url").subscribe(testObserver)

        testObserver.awaitTerminalEvent()
        testObserver.assertValue(specie)
        verify(api).getSpecies("url")
    }

    @Test
    fun `Test film`() {
        val response = FilmResponse(
            "name",
            "crawl"
        )

        val film = Film(
            "name",
            "crawl"
        )

        val testObserver = TestObserver<Film>()
        whenever(api.getCharacterFilms("url")).thenReturn(
            Single.just(response))

        charactersRepositoryImpl.getCharacterFilm("url").subscribe(testObserver)

        testObserver.awaitTerminalEvent()
        testObserver.assertValue(film)
        verify(api).getCharacterFilms("url")
    }

}
