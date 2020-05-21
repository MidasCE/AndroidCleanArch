package com.example.data.permission

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import repository.permission.AppPermissionProvider

class AppPermissionProviderImpl(private val context: Context) :
    AppPermissionProvider {

    private val requiredPermissions =
        listOf(Manifest.permission.ACCESS_FINE_LOCATION)

    override fun isPermissionGranted(): Boolean {
        for (permission in requiredPermissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED)
                return false
        }
        return true
    }
}