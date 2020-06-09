package com.example.app.main.view.presenter

import com.example.app.core.SchedulerFactory
import com.example.app.main.view.CharacterListScreenView
import com.example.app.main.view.model.CharacterItemViewEntity
import com.example.domain.interactor.PermissionInteractor
import io.reactivex.disposables.CompositeDisposable

class CharacterListScreenPresenterImpl(
    private val schedulerFactory: SchedulerFactory,
    private val view: CharacterListScreenView
) : CharacterListScreenPresenter {

    private var compositeDisposable = CompositeDisposable()

    override fun fetchCharacterList(): List<CharacterItemViewEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun searchCharacters(title: String): List<CharacterItemViewEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
