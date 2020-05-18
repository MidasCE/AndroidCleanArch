package com.example.app.di

import com.example.domain.interactor.PermissionInteractor
import com.example.domain.interactor.PermissionInteractorImpl
import dagger.Module
import dagger.Provides
import repository.permission.AppPermissionProvider
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    @Singleton
    fun providePermissionInteractor(appPermissionProvider: AppPermissionProvider): PermissionInteractor =
        PermissionInteractorImpl(appPermissionProvider)
}
