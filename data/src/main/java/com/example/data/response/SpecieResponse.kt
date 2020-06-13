package com.example.data.response

import com.example.domain.model.Specie
import com.google.gson.annotations.SerializedName

data class SpecieResponse(
    @SerializedName("name") val name: String,
    @SerializedName("homeworld") val homeWorldUrl: String?,
    @SerializedName("language") val language: String)

fun SpecieResponse.toDomain(): Specie =
    Specie(name, homeWorldUrl, language)