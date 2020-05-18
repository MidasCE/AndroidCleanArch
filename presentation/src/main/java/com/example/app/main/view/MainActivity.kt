package com.example.app.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.app.R
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
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    private lateinit var mapView: GoogleMap

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
        mapView.moveCamera(CameraUpdateFactory.newLatLng(LatLng(0.0, 0.0)));
    }

    override fun navigateToPermissionSettings() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
