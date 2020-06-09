package com.example.app.di

import com.example.domain.CharactersInteractor
import com.example.domain.CharactersInteractorImpl
import dagger.Module
import dagger.Provides
import repository.CharactersRepository
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    @Singleton
    fun provideCharactersInteractor(charactersRepository: CharactersRepository): CharactersInteractor =
        CharactersInteractorImpl(charactersRepository)
}
