package com.example.app.di

import com.example.app.main.mapscreen.di.MapScreenFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector(modules = [MapScreenFragmentModule::class])
    abstract fun bindMapScreenFragment(): MapScreenFragment
}
