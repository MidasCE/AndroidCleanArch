package com.example.app.di

import android.content.Context
import com.example.data.api.SomeAPI
import repository.permission.AppPermissionProvider
import com.example.data.permission.AppPermissionProviderImpl
import com.example.data.serializer.DateDeserializer
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideLocationProvider(context: Context): AppPermissionProvider {
        return AppPermissionProviderImpl(context)
    }

    @Provides
    @Singleton
    fun provideSomeAPI(retrofit: Retrofit): SomeAPI =
        retrofit.create(SomeAPI::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://somurl.com/")
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .registerTypeAdapter(Date::class.java, DateDeserializer())
        .create()
}
