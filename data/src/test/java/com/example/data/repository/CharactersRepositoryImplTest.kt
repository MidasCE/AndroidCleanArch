package com.example.data.repository

import com.example.data.api.StarwarAPI
import com.example.data.response.CharacterDto
import com.example.data.response.CharactersResponse
import com.example.domain.model.Character
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
            listOf("url"),
            listOf("url"),
            "url"
        )

        val character = Character(
            "name",
            "height",
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
            listOf("url"),
            listOf("url"),
            "url"
        )

        val character = Character(
            "name",
            "height",
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



}
