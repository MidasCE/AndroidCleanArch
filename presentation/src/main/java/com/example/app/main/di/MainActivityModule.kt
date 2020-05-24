package com.example.app.main.di

import android.content.Context
import com.example.app.core.SchedulerFactory
import com.example.app.main.view.MainActivity
import com.example.app.main.view.MainView
import com.example.app.main.view.presenter.MainScreenPresenter
import com.example.app.main.view.presenter.MainScreenPresenterImpl
import com.example.domain.interactor.PermissionInteractor
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    fun provideMainView(mainActivity: MainActivity): MainView = mainActivity

    @Provides
    fun provideMainScreenPresenter(schedulerFactory: SchedulerFactory,
                                   mainView: MainView,
                                   permissionInteractor: PermissionInteractor
    ): MainScreenPresenter = MainScreenPresenterImpl(schedulerFactory, mainView, permissionInteractor)
}
