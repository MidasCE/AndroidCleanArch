package com.example.app.main.mapscreen

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds

interface MapScreenView {

    fun setMapLocation(latLngBounds: LatLngBounds)

    fun zoomMapLocation(latLng: LatLng)

    fun navigateToPermissionSettings()

    fun showMessage(message: String)
}