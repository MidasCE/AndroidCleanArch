package com.example.app.main.view

import android.net.Uri

interface MainView {
    fun navigateToRecordAudioPermissionSettings()

    fun navigatePickSongScreen()

    fun setUpVisualizer(uri: Uri)

    fun showMessage(message: String)
}
