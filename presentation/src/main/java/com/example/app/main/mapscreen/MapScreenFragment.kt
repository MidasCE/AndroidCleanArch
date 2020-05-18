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
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class MapScreenFragment : Fragment(), MapScreenView, OnMapReadyCallback {

    @Inject
    lateinit var presenter: MapScreenPresenter

    private lateinit var mapView: GoogleMap
    private var rootView: View? = null

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_mapscreen, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val supportFragmentManager = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        supportFragmentManager.getMapAsync(this)
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
            REQUEST_CODE_ASK_PERMISSIONS -> {
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
                REQUEST_CODE_ASK_PERMISSIONS
            )
        }
    }

    override fun setMapLocation(latLngBounds: LatLngBounds) {
        mapView.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 0))
    }

    override fun zoomMapLocation(latLng: LatLng) {
        mapView.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12.0f))
    }

    override fun showMessage(message: String) {
        rootView?.let {
            Snackbar.make(it.findViewById(R.id.mapRootView), message, Snackbar.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val REQUEST_CODE_ASK_PERMISSIONS = 123
    }

}
