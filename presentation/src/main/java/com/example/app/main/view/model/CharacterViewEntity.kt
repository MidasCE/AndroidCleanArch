package com.example.app.main.view.model

import android.os.Parcelable
import com.example.domain.model.Character
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterViewEntity(val title: String,
                               val height: String,
                               val filmsUrl: List<String>,
                               val speciesUrl: List<String>) : Parcelable

fun Character.toViewEntity() : CharacterViewEntity =
    CharacterViewEntity(name, height, filmsUrl, speciesUrl)
