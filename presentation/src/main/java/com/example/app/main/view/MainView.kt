package com.example.app.main.view

import android.net.Uri

interface MainView {
    fun navigateToRecordAudioPermissionSettings()

    fun navigatePickSongScreen()

    fun showMediaButton()

    fun setUpVisualizer(uri: Uri)

    fun showMessage(message: String)
}
