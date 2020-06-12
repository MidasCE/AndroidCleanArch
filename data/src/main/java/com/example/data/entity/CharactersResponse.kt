package com.example.data.entity

import com.example.domain.model.Character
import com.google.gson.annotations.SerializedName

data class CharactersResponse(
    @SerializedName("results") val results: List<CharacterDto>
)

data class CharacterDto(
    @SerializedName("name") val name: String,
    @SerializedName("height") val height: String,
    @SerializedName("mass") val mass: String,
    @SerializedName("hair_color") val hairColor: String,
    @SerializedName("skin_color") val skinColor: String,
    @SerializedName("eye_color") val eyeColor: String,
    @SerializedName("birth_year") val birthYear: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("url") val url: String
)

fun CharacterDto.toDomain(): Character =
    Character(name, height, mass, hairColor, skinColor, eyeColor, birthYear, gender, url)

