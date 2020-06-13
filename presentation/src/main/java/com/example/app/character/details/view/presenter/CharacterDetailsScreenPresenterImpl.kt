package com.example.app.character.details.view.presenter

import com.example.app.character.details.view.CharacterDetailsView
import com.example.app.character.details.view.model.CharacterHomeWorldViewEntity
import com.example.app.character.details.view.model.FilmViewEntity
import com.example.app.core.SchedulerFactory
import com.example.app.main.view.model.CharacterViewEntity
import com.example.domain.CharactersInteractor
import com.example.domain.model.HomeWorld
import com.example.domain.model.Specie
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.toObservable

class CharacterDetailsScreenPresenterImpl(
    private val schedulerFactory: SchedulerFactory,
    private val view: CharacterDetailsView,
    private val charactersInteractor: CharactersInteractor
) : CharacterDetailsScreenPresenter {

    //TODO currently, now only using memory cache to saved selected characters details.
    @Volatile
    private var characterViewEntity: CharacterViewEntity? = null

    private var compositeDisposable = CompositeDisposable()

    @Synchronized
    override fun updateCharacterDetails(characterViewEntity: CharacterViewEntity) {
        this.characterViewEntity = characterViewEntity
        updateSpeciesDetail(characterViewEntity.speciesUrl)
        updateFilmsDetail(characterViewEntity.filmsUrl)
    }

    override fun currentCharacterViewEntity(): CharacterViewEntity? = characterViewEntity

    private fun updateSpeciesDetail(speciesUrl: List<String>) {
        compositeDisposable +=
            speciesUrl.toObservable().flatMap { url ->
                charactersInteractor.getSpecies(url).toObservable()
            }.switchMap { specie ->
                specie.homeWorldUrl?.let { homeWorldUrl ->
                    charactersInteractor.getHomeWorld(homeWorldUrl).map { homeWorld ->
                        Pair(specie, homeWorld)
                    }.toObservable()
                } ?: Observable.just(Pair<Specie, HomeWorld?>(specie, null))
            }.toList()
                .subscribeOn(schedulerFactory.io())
                .observeOn(schedulerFactory.main())
                .subscribe { list ->
                    val entityList = mutableListOf<CharacterHomeWorldViewEntity>()
                    list.map { (specie, homeWorld) ->
                        val entity = CharacterHomeWorldViewEntity(
                            specie.name,
                            specie.language,
                            homeWorld?.name,
                            homeWorld?.population
                        )
                        entityList.add(entity)
                    }
                    view.updateCharacterBackgroundDetails(entityList)
                }
    }

    private fun updateFilmsDetail(filmsUrl: List<String>) {
        compositeDisposable +=
            filmsUrl.toObservable().flatMap { url ->
                charactersInteractor.getCharacterFilm(url).toObservable()
            }.toList()
                .subscribeOn(schedulerFactory.io())
                .observeOn(schedulerFactory.main())
                .subscribe { list ->
                    val entityList = mutableListOf<FilmViewEntity>()
                    list.map { film ->
                        val entity = FilmViewEntity(
                            film.title,
                            film.openingCrawl
                        )
                        entityList.add(entity)
                    }
                    view.updateCharacterFilmDetails(entityList)
                }
    }

}
