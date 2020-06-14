package com.example.app.character.details.view.presenter

import com.example.app.character.details.view.CharacterDetailsView
import com.example.app.character.details.view.model.CharacterHomeWorldViewEntity
import com.example.app.character.details.view.model.FilmViewEntity
import com.example.app.core.SchedulerFactory
import com.example.app.main.view.model.CharacterViewEntity
import com.example.domain.CharactersInteractor
import com.example.domain.model.Film
import com.example.domain.model.HomeWorld
import com.example.domain.model.Specie
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CharacterDetailsScreenPresenterImplTest {

    @Mock
    lateinit var schedulerFactory: SchedulerFactory

    @Mock
    lateinit var view: CharacterDetailsView

    @Mock
    lateinit var charactersInteractor: CharactersInteractor

    private lateinit var presenter: CharacterDetailsScreenPresenterImpl

    private lateinit var ioScheduler: TestScheduler

    private lateinit var mainScheduler: TestScheduler

    @Before
    fun setUp() {
        ioScheduler = TestScheduler()
        mainScheduler = TestScheduler()

        whenever(schedulerFactory.io()).thenReturn(ioScheduler)
        whenever(schedulerFactory.main()).thenReturn(mainScheduler)

        presenter = CharacterDetailsScreenPresenterImpl(
            schedulerFactory,
            view,
            charactersInteractor
        )
    }

    @Test
    fun `updateCharacterDetails | no url`() {
        val viewEntity = CharacterViewEntity(
            "name",
            "birthYear",
            "heightCm",
            "heightInch",
            emptyList(),
            emptyList()
        )

        presenter.updateCharacterDetails(viewEntity)

        assertEquals(viewEntity, presenter.currentCharacterViewEntity())
        verify(charactersInteractor, never()).getSpecie(anyString())
        verify(charactersInteractor, never()).getCharacterFilm(anyString())
    }

    @Test
    fun `updateCharacterDetails | species url without homeworld url`() {
        val viewEntity = CharacterViewEntity(
            "name",
            "birthYear",
            "heightCm",
            "heightInch",
            emptyList(),
            listOf("url")
        )

        val homeWorldViewEntity = CharacterHomeWorldViewEntity(
            "name",
            "population",
            null,
            null
        )

        whenever(charactersInteractor.getSpecie("url")).thenReturn(
            Single.just(
                Specie(
                    "name",
                    null,
                    "population"
                )
            )
        )

        presenter.updateCharacterDetails(viewEntity)

        ioScheduler.triggerActions()
        mainScheduler.triggerActions()

        verify(charactersInteractor).getSpecie("url")
        verify(view).updateCharacterBackgroundDetails(listOf(homeWorldViewEntity))
        assertEquals(viewEntity, presenter.currentCharacterViewEntity())

        verify(charactersInteractor, never()).getHomeWorld(anyString())
        verify(charactersInteractor, never()).getCharacterFilm(anyString())
    }

    @Test
    fun `updateCharacterDetails | species url with home world url`() {
        val viewEntity = CharacterViewEntity(
            "name",
            "birthYear",
            "heightCm",
            "heightInch",
            emptyList(),
            listOf("url")
        )

        val homeWorldViewEntity = CharacterHomeWorldViewEntity(
            "name",
            "population",
            "homeWorldName",
            "population"
        )

        whenever(charactersInteractor.getSpecie("url")).thenReturn(
            Single.just(
                Specie(
                    "name",
                    "homeWorldUrl",
                    "population"
                )
            )
        )

        whenever(charactersInteractor.getHomeWorld("homeWorldUrl")).thenReturn(
            Single.just(
                HomeWorld(
                    "homeWorldName",
                    "population"
                )
            )
        )

        presenter.updateCharacterDetails(viewEntity)

        ioScheduler.triggerActions()
        mainScheduler.triggerActions()

        verify(charactersInteractor).getSpecie("url")
        verify(charactersInteractor).getHomeWorld("homeWorldUrl")
        verify(view).updateCharacterBackgroundDetails(listOf(homeWorldViewEntity))
        assertEquals(viewEntity, presenter.currentCharacterViewEntity())

        verify(charactersInteractor, never()).getCharacterFilm(anyString())
    }

    @Test
    fun `updateCharacterDetails | film url`() {
        val viewEntity = CharacterViewEntity(
            "name",
            "birthYear",
            "heightCm",
            "heightInch",
            listOf("url"),
                    emptyList()
        )

        val filmViewEntity = FilmViewEntity(
            "title",
            "crawl"
        )

        whenever(charactersInteractor.getCharacterFilm("url")).thenReturn(
            Single.just(
                Film(
                    "title",
                    "crawl"
                )
            )
        )

        presenter.updateCharacterDetails(viewEntity)

        ioScheduler.triggerActions()
        mainScheduler.triggerActions()

        verify(charactersInteractor).getCharacterFilm("url")
        verify(view).updateCharacterFilmDetails(listOf(filmViewEntity))
        assertEquals(viewEntity, presenter.currentCharacterViewEntity())

        verify(charactersInteractor, never()).getSpecie(anyString())
    }

}
