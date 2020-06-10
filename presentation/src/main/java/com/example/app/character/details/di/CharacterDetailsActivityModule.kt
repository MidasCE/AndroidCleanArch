package com.example.app.character.details.di

import com.example.app.character.details.view.CharacterDetailsActivity
import com.example.app.character.details.view.CharacterDetailsView
import dagger.Module
import dagger.Provides

@Module
class CharacterDetailsActivityModule {

    @Provides
    fun provideMainView(activity: CharacterDetailsActivity): CharacterDetailsView = activity
}
