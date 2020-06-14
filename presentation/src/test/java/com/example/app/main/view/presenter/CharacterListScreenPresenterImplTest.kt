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

    private lateinit var presenter: CharacterListScreenPresenterImpl

    private lateinit var ioScheduler: TestScheduler

    private lateinit var mainScheduler: TestScheduler

    @Before
    fun setUp() {
        ioScheduler = TestScheduler()
        mainScheduler = TestScheduler()

        whenever(schedulerFactory.io()).thenReturn(ioScheduler)
        whenever(schedulerFactory.main()).thenReturn(mainScheduler)

        presenter = CharacterListScreenPresenterImpl(schedulerFactory, view, charactersInteractor)
    }

    @Test
    fun `Test searchCharacters`() {
        val character = Character(
            "name",
            "birthYear",
            "height",
            emptyList(),
            emptyList(),
            "url"
        )

        val viewEntity = CharacterViewEntity(
            "name",
            "birthYear",
            "165",
            "5' 4\"",
            emptyList(),
            emptyList()
        )
        whenever(charactersInteractor.searchCharacters("name")).thenReturn(Single.just(listOf(character)))

        presenter.searchCharacters("name")

        ioScheduler.triggerActions()
        mainScheduler.triggerActions()

        verify(view).hideLoading()
        verify(view).updateCharacterList(listOf(viewEntity))
    }

    @Test
    fun `Test searchCharacters return error`() {
        whenever(charactersInteractor.searchCharacters("name")).thenReturn(Single.error(Throwable()))

        presenter.searchCharacters("name")

        ioScheduler.triggerActions()
        mainScheduler.triggerActions()

        verify(view).hideLoading()
        verify(view).showError()
    }

    @Test
    fun `Test fetchCharacters`() {
        val character = Character(
            "name",
            "birthYear",
            "height",
            emptyList(),
            emptyList(),
            "url"
        )

        val viewEntity = CharacterViewEntity(
            "name",
            "birthYear",
            "165",
            "5' 4\"",
            emptyList(),
            emptyList()
        )
        whenever(charactersInteractor.fetchCharacters(1)).thenReturn(Single.just(listOf(character)))

        presenter.fetchCharacterList()

        ioScheduler.triggerActions()
        mainScheduler.triggerActions()

        verify(view).hideLoading()
        verify(view).addCharacterList(listOf(viewEntity))
    }

    @Test
    fun `Test fetchCharacters return error`() {
        whenever(charactersInteractor.fetchCharacters(1)).thenReturn(Single.error(Throwable()))

        presenter.fetchCharacterList()

        ioScheduler.triggerActions()
        mainScheduler.triggerActions()

        verify(view).hideLoading()
        verify(view).showError()
    }
}
