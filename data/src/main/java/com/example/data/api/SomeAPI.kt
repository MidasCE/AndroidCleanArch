package com.example.data.api

import com.example.data.entity.Response
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SomeAPI {

    @GET("endpoint")
    fun getSomething(@Query("base") base: String): Single<Response>
}
