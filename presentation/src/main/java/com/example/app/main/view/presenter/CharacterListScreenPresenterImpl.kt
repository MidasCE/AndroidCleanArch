package com.example.app.main.view.presenter

import com.example.app.core.SchedulerFactory
import com.example.app.main.view.CharacterListScreenView
import com.example.app.main.view.model.CharacterViewEntity
import com.example.domain.CharactersInteractor
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign

class CharacterListScreenPresenterImpl(
    private val schedulerFactory: SchedulerFactory,
    private val view: CharacterListScreenView,
    private val charactersInteractor: CharactersInteractor
) : CharacterListScreenPresenter {

    private var currentPage = 1

    private var compositeDisposable = CompositeDisposable()

    override fun fetchCharacterList() {
        compositeDisposable += charactersInteractor.fetchCharacters(currentPage)
            .subscribeOn(schedulerFactory.io())
            .observeOn(schedulerFactory.main())
            .map { characters ->
                characters.map { character ->
                    CharacterViewEntity(character.name, character.imageUrl)
                }
            }
            .subscribe({ characters ->
                view.hideLoading()
                view.updateCharacterList(characters)
            }, {
                view.hideLoading()
                view.showError()
            })
    }

    override fun searchCharacters(title: String) {
        compositeDisposable += charactersInteractor.searchCharacters(title)
            .subscribeOn(schedulerFactory.io())
            .observeOn(schedulerFactory.main())
            .map { characters ->
                characters.map { character ->
                    CharacterViewEntity(character.name, character.imageUrl)
                }
            }
            .subscribe({ characters ->
                view.hideLoading()
                view.updateCharacterList(characters)
            }, {
                view.hideLoading()
                view.showError()
            })
    }

}
