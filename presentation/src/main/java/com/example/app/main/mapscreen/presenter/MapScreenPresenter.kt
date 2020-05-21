package com.example.app.main.mapscreen.presenter

interface MapScreenPresenter {

    fun initPermission()

    fun onReceivedLocationPermissionResponse(isGranted: Boolean)

}
