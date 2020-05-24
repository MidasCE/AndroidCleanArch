package com.example.app.main.view.presenter

import android.net.Uri
import com.example.app.core.SchedulerFactory
import com.example.app.main.view.MainView
import com.example.domain.interactor.PermissionInteractor

class MainScreenPresenterImpl(
    private val schedulerFactory: SchedulerFactory,
    private val view: MainView,
    private val permissionInteractor: PermissionInteractor
) : MainScreenPresenter {

    private var currentSong : Uri? = null

    override fun onClickPickSong() {
        if (permissionInteractor.isRecordAudioPermissionGranted()) {
            view.navigatePickSongScreen()
        } else {
            view.navigateToRecordAudioPermissionSettings()
        }
    }

    override fun onSongPicked(uri: Uri) {
        currentSong = uri

        currentSong?.let {
            view.showMediaButton()
            view.setUpVisualizer(it)
        }
    }

    override fun onReceivedLocationPermissionResponse(isGranted: Boolean) {
        view.showMessage("Permission granted. Now you can select song.")
    }


}
