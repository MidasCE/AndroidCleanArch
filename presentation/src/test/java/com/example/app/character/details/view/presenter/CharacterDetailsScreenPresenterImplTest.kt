package com.example.app.character.details.view.presenter

import com.example.app.main.view.model.CharacterViewEntity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CharacterDetailsScreenPresenterImplTest {

    private lateinit var presenter: CharacterDetailsScreenPresenterImpl

    @Before
    fun setUp() {
        presenter = CharacterDetailsScreenPresenterImpl()
    }

    @Test
    fun `updateCharacterDetails should return latest entity`() {
        val viewEntity = CharacterViewEntity(
            "name",
            "height",
            "mass",
            "hairColor",
            "skinColor",
            "eyeColor",
            "birthYear",
            "gender")

        presenter.updateCharacterDetails(viewEntity)

        assertEquals(viewEntity, presenter.currentCharacterViewEntity())
    }

}
