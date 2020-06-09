package com.example.app.di

import com.example.data.api.StarwarAPI
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
    fun provideStarwarAPI(retrofit: Retrofit): StarwarAPI =
        retrofit.create(StarwarAPI::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://swapi.dev/api/")
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
