package com.example.data.response

import com.example.domain.model.Film
import com.google.gson.annotations.SerializedName

data class FilmResponse(
    @SerializedName("title") val title: String,
    @SerializedName("opening_crawl") val openingCrawl: String
)

fun FilmResponse.toDomain(): Film =
    Film(title, openingCrawl)
