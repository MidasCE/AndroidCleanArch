package com.example.domain


import com.example.domain.model.Character
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
            "height '",
            "mass'",
            "hairColor'",
            "skinColor'",
            "eyeColor",
            "birthYear",
            "gender",
            "url")

        whenever(repository.searchCharacters("name")).thenReturn(Single.just(listOf(character)))

        charactersInteractor.searchCharacters("name").test()
            .assertValue(listOf(character))
    }

    @Test
    fun `fetchCharacters`() {
        val character = Character(
            "name",
            "height '",
            "mass'",
            "hairColor'",
            "skinColor'",
            "eyeColor",
            "birthYear",
            "gender",
            "url")

        whenever(repository.fetchCharacters(1)).thenReturn(Single.just(listOf(character)))

        charactersInteractor.fetchCharacters(1).test()
            .assertValue(listOf(character))
    }

}
