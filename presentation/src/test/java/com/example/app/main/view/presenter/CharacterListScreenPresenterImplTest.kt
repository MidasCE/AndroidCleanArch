package com.example.app.main.view.presenter

import com.example.app.core.SchedulerFactory
import com.example.app.main.view.CharacterListScreenView
import com.example.app.main.view.model.CharacterViewEntity
import com.example.domain.CharactersInteractor
import com.example.domain.model.Character
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CharacterListScreenPresenterImplTest {

    @Mock
    lateinit var schedulerFactory: SchedulerFactory

    @Mock
    lateinit var view: CharacterListScreenView

    @Mock
    lateinit var charactersInteractor: CharactersInteractor

    private lateinit var mapScreenPresenterImpl: CharacterListScreenPresenterImpl

    private lateinit var ioScheduler: TestScheduler

    private lateinit var mainScheduler: TestScheduler

    @Before
    fun setUp() {
        ioScheduler = TestScheduler()
        mainScheduler = TestScheduler()

        whenever(schedulerFactory.io()).thenReturn(ioScheduler)
        whenever(schedulerFactory.main()).thenReturn(mainScheduler)

        mapScreenPresenterImpl = CharacterListScreenPresenterImpl(schedulerFactory, view, charactersInteractor)
    }

    @Test
    fun `Test searchCharacters`() {
        val character = Character(
            "name",
            "height",
            "mass",
            "hairColor",
            "skinColor",
            "eyeColor",
            "birthYear",
            "gender",
            "url")

        val viewEntity = CharacterViewEntity(
            "name",
            "height",
            "mass",
            "hairColor",
            "skinColor",
            "eyeColor",
            "birthYear",
            "gender")
        whenever(charactersInteractor.searchCharacters("name")).thenReturn(Single.just(listOf(character)))

        ioScheduler.triggerActions()
        mainScheduler.triggerActions()

        verify(view).hideLoading()
        verify(view).updateCharacterList(listOf(viewEntity))
    }

    @Test
    fun `Test searchCharacters return error`() {
        whenever(charactersInteractor.searchCharacters("name")).thenReturn(Single.error(Throwable()))

        ioScheduler.triggerActions()
        mainScheduler.triggerActions()

        verify(view).hideLoading()
        verify(view).showError()
    }

    @Test
    fun `Test fetchCharacters`() {
        val character = Character(
            "name",
            "height",
            "mass",
            "hairColor",
            "skinColor",
            "eyeColor",
            "birthYear",
            "gender",
            "url")

        val viewEntity = CharacterViewEntity(
            "name",
            "height",
            "mass",
            "hairColor",
            "skinColor",
            "eyeColor",
            "birthYear",
            "gender")
        whenever(charactersInteractor.fetchCharacters(1)).thenReturn(Single.just(listOf(character)))

        ioScheduler.triggerActions()
        mainScheduler.triggerActions()

        verify(view).hideLoading()
        verify(view).addCharacterList(listOf(viewEntity))
    }

    @Test
    fun `Test fetchCharacters return error`() {
        whenever(charactersInteractor.fetchCharacters(1)).thenReturn(Single.error(Throwable()))

        ioScheduler.triggerActions()
        mainScheduler.triggerActions()

        verify(view).hideLoading()
        verify(view).showError()
    }
}
