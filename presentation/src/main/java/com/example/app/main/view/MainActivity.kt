package com.example.app.main.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentTransaction
import com.example.app.R
import com.example.app.main.mapscreen.MapScreenFragment
import com.example.app.main.mapscreen.presenter.MapScreenPresenter
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

class MainActivity : MainView, AppCompatActivity(), HasAndroidInjector {


    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    companion object {
        const val REQUEST_CODE_ASK_PERMISSIONS = 123
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        val newCurrentFragment = supportFragmentManager.findFragmentByTag("tag")
            ?: MapScreenFragment()
        transaction.replace(R.id.fragment_container, newCurrentFragment, "tag")
        transaction.commit()
    }
}
