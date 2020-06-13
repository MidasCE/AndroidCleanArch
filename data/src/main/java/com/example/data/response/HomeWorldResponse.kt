package com.example.data.response

import com.example.domain.model.HomeWorld
import com.google.gson.annotations.SerializedName

data class HomeWorldResponse(
    @SerializedName("name") val name: String,
    @SerializedName("population") val population: String
)

fun HomeWorldResponse.toDomain(): HomeWorld =
    HomeWorld(name, population)