package com.example.app.character.details.di

import com.example.app.character.details.view.CharacterDetailsActivity
import com.example.app.character.details.view.CharacterDetailsView
import com.example.app.character.details.view.adapter.CharacterFilmItemAdapter
import com.example.app.character.details.view.adapter.CharacterHomeWorldItemAdapter
import com.example.app.character.details.view.presenter.CharacterDetailsScreenPresenter
import com.example.app.character.details.view.presenter.CharacterDetailsScreenPresenterImpl
import com.example.app.core.SchedulerFactory
import com.example.domain.CharactersInteractor
import dagger.Module
import dagger.Provides

@Module
class CharacterDetailsActivityModule {

    @Provides
    fun provideMainView(activity: CharacterDetailsActivity): CharacterDetailsView = activity

    @Provides
    fun provideCharacterListScreenPresenter(
        schedulerFactory: SchedulerFactory,
        charactersInteractor: CharactersInteractor,
        view: CharacterDetailsView): CharacterDetailsScreenPresenter =
        CharacterDetailsScreenPresenterImpl(schedulerFactory, view, charactersInteractor)

    @Provides
    fun provideCharacterFilmItemAdapter(): CharacterFilmItemAdapter =
        CharacterFilmItemAdapter()

    @Provides
    fun provideCharacterItemAdapter(): CharacterHomeWorldItemAdapter =
        CharacterHomeWorldItemAdapter()
}
