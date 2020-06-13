package com.example.data.api

import com.example.data.response.CharactersResponse
import com.example.data.response.FilmResponse
import com.example.data.response.HomeWorldResponse
import com.example.data.response.SpecieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface StarwarAPI {

    @GET("people/")
    fun searchCharacters(@Query("search") title: String): Single<CharactersResponse>

    @GET("people/")
    fun fetchCharacters(@Query("page") page: Int): Single<CharactersResponse>

    @GET
    fun getHomeWorld(@Url url: String): Single<HomeWorldResponse>

    @GET
    fun getSpecies(@Url url: String): Single<SpecieResponse>

    @GET
    fun getCharacterFilms(@Url url: String): Single<FilmResponse>
}
