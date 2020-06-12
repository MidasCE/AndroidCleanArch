package com.example.app.main.di

import com.example.app.core.SchedulerFactory
import com.example.app.main.view.CharacterItemAdapter
import com.example.app.main.view.MainActivity
import com.example.app.main.view.CharacterListScreenView
import com.example.app.main.view.presenter.CharacterListScreenPresenter
import com.example.app.main.view.presenter.CharacterListScreenPresenterImpl
import com.example.domain.CharactersInteractor
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    fun provideMainView(mainActivity: MainActivity): CharacterListScreenView = mainActivity

    @Provides
    fun provideCharacterListScreenPresenter(
        schedulerFactory: SchedulerFactory,
        charactersInteractor: CharactersInteractor,
        view: CharacterListScreenView
    ): CharacterListScreenPresenter =
        CharacterListScreenPresenterImpl(
            schedulerFactory,
            view,
            charactersInteractor
        )

    @Provides
    fun provideCharacterItemAdapter(
        mainActivity: MainActivity
    ): CharacterItemAdapter =
        CharacterItemAdapter(mainActivity)
}
