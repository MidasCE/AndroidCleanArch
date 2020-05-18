package com.example.app.main.presenter

import com.example.app.core.SchedulerFactory
import com.example.app.main.view.MainView
import com.example.domain.interactor.PermissionInteractor
import io.reactivex.disposables.CompositeDisposable

class MainPresenterImpl(
    private val schedulerFactory: SchedulerFactory,
    private val view: MainView,
    private val permissionInteractor: PermissionInteractor
) : MainPresenter {

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
