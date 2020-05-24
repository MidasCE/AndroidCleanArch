package com.example.app.main.view.presenter

import android.net.Uri

interface MainScreenPresenter {

    fun onClickPickSong()

    fun onSongPicked(uri: Uri)

    fun onReceivedLocationPermissionResponse(isGranted: Boolean)

}
