package com.example.app.character.details.di

import com.example.app.character.details.view.CharacterDetailsActivity
import com.example.app.character.details.view.CharacterDetailsView
import com.example.app.character.details.view.presenter.CharacterDetailsScreenPresenter
import com.example.app.character.details.view.presenter.CharacterDetailsScreenPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class CharacterDetailsActivityModule {

    @Provides
    fun provideMainView(activity: CharacterDetailsActivity): CharacterDetailsView = activity

    @Provides
    fun provideCharacterListScreenPresenter(): CharacterDetailsScreenPresenter =
        CharacterDetailsScreenPresenterImpl()
}
