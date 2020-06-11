package com.example.app.character.details.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.app.R
import com.example.app.character.details.view.presenter.CharacterDetailsScreenPresenter
import com.example.app.main.view.model.CharacterViewEntity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class CharacterDetailsActivity: CharacterDetailsView, AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var presenter: CharacterDetailsScreenPresenter

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    private lateinit var nameTextView: TextView
    private lateinit var heightTextView: TextView
    private lateinit var massTextView: TextView
    private lateinit var hairTextView: TextView
    private lateinit var skinTextView: TextView
    private lateinit var eyeTextView: TextView
    private lateinit var birthDayTextView: TextView
    private lateinit var genderTextView: TextView

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chars_details)

        val viewEntity = intent.extras?.get(CHARACTERS_KEY)
        if (viewEntity == null || !viewEntity is CharacterViewEntity) {
            finish()
        }
        initView(viewEntity as CharacterViewEntity)
    }

    private fun initView(viewEntity: CharacterViewEntity) {
        nameTextView = findViewById(R.id.nameTextView)
        heightTextView = findViewById(R.id.heightTextView)
        massTextView = findViewById(R.id.massTextView)
        hairTextView = findViewById(R.id.hairTextView)
        skinTextView = findViewById(R.id.skinTextView)
        eyeTextView = findViewById(R.id.eyeTextView)
        birthDayTextView = findViewById(R.id.birthDayTextView)
        genderTextView = findViewById(R.id.genderTextView)
        updateCharacterDetail(viewEntity)
    }

    private fun updateCharacterDetail(entity: CharacterViewEntity) {
        entity.apply {
            nameTextView.text = title
            heightTextView.text = height
            massTextView.text = mass
            hairTextView.text = hairColor
            skinTextView.text = skinColor
            eyeTextView.text = eyeColor
            birthDayTextView.text = birthDay
            genderTextView.text = gender

        }
    }

    companion object {
        const val CHARACTERS_KEY = "CHARACTERS_KEY"
    }

}