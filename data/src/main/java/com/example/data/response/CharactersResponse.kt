package com.example.data.response

import com.example.domain.model.Character
import com.google.gson.annotations.SerializedName

data class CharactersResponse(
    @SerializedName("results") val results: List<CharacterDto>
)

data class CharacterDto(
    @SerializedName("name") val name: String,
    @SerializedName("height") val height: String,
    @SerializedName("birth_year") val birthYear: String,
    @SerializedName("films") val filmsUrl: List<String>,
    @SerializedName("species") val speciesUrl: List<String>,
    @SerializedName("url") val url: String
)

fun CharacterDto.toDomain(): Character =
    Character(name, birthYear, height, filmsUrl, speciesUrl, url)

