package com.example.app.di

import android.content.Context
import repository.permission.AppPermissionProvider
import com.example.data.permission.AppPermissionProviderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun providePermissionProvider(context: Context): AppPermissionProvider {
        return AppPermissionProviderImpl(context)
    }
}
