package com.example.app.main.view.model

import android.os.Parcelable
import com.example.domain.model.Character
import kotlinx.android.parcel.Parcelize
import kotlin.math.floor

@Parcelize
data class CharacterViewEntity(val title: String,
                               val birthYear: String,
                               val heightCm: String,
                               val heightInch: String,
                               val filmsUrl: List<String>,
                               val speciesUrl: List<String>) : Parcelable

fun Character.toViewEntity() : CharacterViewEntity {
    val heightInch = height.toDoubleOrNull()?.let { heightCm ->
        val feetPart = floor(heightCm / 2.54 / 12).toInt()
        val inchesPart = floor(heightCm / 2.54 - feetPart * 12).toInt()
        "$feetPart' $inchesPart''"
    }.orEmpty()

    return CharacterViewEntity(name, birthYear, height, heightInch, filmsUrl, speciesUrl)
}
