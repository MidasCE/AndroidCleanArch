package com.example.app.main.presenter

interface MainPresenter {

    fun initPermission()

    fun onReceivedLocationPermissionResponse(isGranted: Boolean)

}
