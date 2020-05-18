package com.example.domain.interactor

import repository.permission.AppPermissionProvider

class PermissionInteractorImpl(private val appPermissionProvider: AppPermissionProvider)
    : PermissionInteractor {

    override fun isPermissionGranted(): Boolean =
        appPermissionProvider.isPermissionGranted()
}
