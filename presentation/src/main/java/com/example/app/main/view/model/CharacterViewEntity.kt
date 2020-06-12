package com.example.app.main.view.model

import android.os.Parcelable
import com.example.domain.model.Character
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterViewEntity(val title: String,
                               val height: String,
                               val mass: String,
                               val hairColor: String,
                               val skinColor: String,
                               val eyeColor: String,
                               val birthDay: String,
                               val gender: String) : Parcelable

fun Character.toViewEntity() : CharacterViewEntity =
    CharacterViewEntity(name, height, mass, hairColor, skinColor, eyeColor, birthYear, gender)
