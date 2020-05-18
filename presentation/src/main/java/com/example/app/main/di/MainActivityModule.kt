package com.example.app.main.di

import com.example.app.core.SchedulerFactory
import com.example.app.main.presenter.MainPresenter
import com.example.app.main.presenter.MainPresenterImpl
import com.example.app.main.view.MainActivity
import com.example.app.main.view.MainView
import com.example.domain.interactor.PermissionInteractor
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    fun provideMainView(mainActivity: MainActivity): MainView = mainActivity

    @Provides
    fun provideMainActivityPresenter(
        schedulerFactory: SchedulerFactory,
        mainView: MainView,
        permissionInteractor: PermissionInteractor
    ): MainPresenter =
        MainPresenterImpl(
            schedulerFactory,
            mainView,
            permissionInteractor
        )
}
