package com.example.data.entity

import com.google.gson.annotations.SerializedName

data class CharactersResponse(
    @SerializedName("results") val results: String
)

data class CharacterResponse(val name: String)
