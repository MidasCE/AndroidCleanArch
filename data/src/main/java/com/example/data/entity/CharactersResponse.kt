package com.example.data.entity

import com.example.domain.model.Character
import com.google.gson.annotations.SerializedName

data class CharactersResponse(
    @SerializedName("results") val results: List<CharacterDto>
)

data class CharacterDto(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)

fun CharacterDto.toDomain(): Character =
    Character(name, url)

