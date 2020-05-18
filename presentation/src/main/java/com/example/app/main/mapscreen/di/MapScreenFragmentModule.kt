package com.example.app.main.mapscreen.di

import com.example.app.core.SchedulerFactory
import com.example.app.main.mapscreen.MapScreenFragment
import com.example.app.main.mapscreen.MapScreenView
import com.example.app.main.mapscreen.presenter.MapScreenPresenter
import com.example.app.main.mapscreen.presenter.MapScreenPresenterImpl
import com.example.domain.interactor.PermissionInteractor
import dagger.Module
import dagger.Provides

@Module
class MapScreenFragmentModule {

    @Provides
    fun provideMapScreenView(fragment: MapScreenFragment) : MapScreenView = fragment

    @Provides
    fun provideMainActivityPresenter(
        schedulerFactory: SchedulerFactory,
        mapView: MapScreenView,
        permissionInteractor: PermissionInteractor
    ): MapScreenPresenter =
        MapScreenPresenterImpl(
            schedulerFactory,
            mapView,
            permissionInteractor
        )
}
