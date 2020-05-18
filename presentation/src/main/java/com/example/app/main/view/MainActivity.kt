package com.example.app.main.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.app.R
import com.example.app.main.presenter.MainPresenter
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : MainView, AppCompatActivity(), HasAndroidInjector, OnMapReadyCallback {

    @Inject
    lateinit var presenter: MainPresenter

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    private lateinit var mapView: GoogleMap

    companion object {
        const val REQUEST_CODE_ASK_PERMISSIONS = 123
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map)
                as SupportMapFragment
        mapFragment.getMapAsync(this)
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
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            REQUEST_CODE_ASK_PERMISSIONS
        )
    }
}
