package com.example.app.di

import com.example.app.character.details.di.CharacterDetailsActivityModule
import com.example.app.character.details.view.CharacterDetailsActivity
import com.example.app.main.di.MainActivityModule
import com.example.app.main.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [CharacterDetailsActivityModule::class])
    abstract fun characterDetailsActivity(): CharacterDetailsActivity
}
