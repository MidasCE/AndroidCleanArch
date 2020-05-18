package com.example.app.main.mapscreen

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.app.R
import com.example.app.main.mapscreen.presenter.MapScreenPresenter
import com.example.app.main.view.MainActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MapScreenFragment : Fragment(), MapScreenView, OnMapReadyCallback {

    @Inject
    lateinit var presenter: MapScreenPresenter

    private lateinit var mapView: GoogleMap

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mapscreen, container, false)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mapView = googleMap
        mapView.moveCamera(CameraUpdateFactory.newLatLng(LatLng(0.0, 0.0)))

        presenter.initPermission()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            MainActivity.REQUEST_CODE_ASK_PERMISSIONS -> {
                // If request is cancelled, the result arrays are empty.
                presenter.onReceivedLocationPermissionResponse(
                    (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                )
                return
            }
        }
    }

    override fun navigateToPermissionSettings() {
        activity?.let {
            ActivityCompat.requestPermissions(
                it,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION
                ),
                MainActivity.REQUEST_CODE_ASK_PERMISSIONS
            )
        }
    }

}
