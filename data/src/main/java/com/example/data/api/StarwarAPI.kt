package com.example.data.api

import com.example.data.entity.CharactersResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface StarwarAPI {

    @GET("people/")
    fun searchCharacters(@Query("search") title: String): Single<CharactersResponse>
}
