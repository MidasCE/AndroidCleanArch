package com.example.app.main.mapscreen.presenter

import com.example.app.core.SchedulerFactory
import com.example.app.main.mapscreen.MapScreenView
import com.example.domain.interactor.PermissionInteractor
import io.reactivex.disposables.CompositeDisposable

class MapScreenPresenterImpl(
    private val schedulerFactory: SchedulerFactory,
    private val view: MapScreenView,
    private val permissionInteractor: PermissionInteractor
) : MapScreenPresenter {

    private var compositeDisposable = CompositeDisposable()

    override fun initPermission() {
        if (!permissionInteractor.isPermissionGranted()) {
            view.navigateToPermissionSettings()
        }
    }
    override fun onReceivedLocationPermissionResponse(isGranted: Boolean) {
        //TODO ready for any logic
    }

}
