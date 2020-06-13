package com.example.app.character.details.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.character.details.view.adapter.CharacterFilmItemAdapter
import com.example.app.character.details.view.adapter.CharacterHomeWorldItemAdapter
import com.example.app.character.details.view.model.CharacterHomeWorldViewEntity
import com.example.app.character.details.view.model.FilmViewEntity
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
    lateinit var homeWorldItemAdapter: CharacterHomeWorldItemAdapter

    @Inject
    lateinit var filmAdapter: CharacterFilmItemAdapter

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    private lateinit var nameTextView: TextView
    private lateinit var heightTextView: TextView
    private lateinit var homeWorldrecyclerView: RecyclerView
    private lateinit var filmRecyclerView: RecyclerView

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chars_details)

        val viewEntity = intent.extras?.get(CHARACTERS_KEY)
        if (viewEntity == null || viewEntity !is CharacterViewEntity) {
            finish()
        }
        initView(viewEntity as CharacterViewEntity)
    }

    private fun initView(viewEntity: CharacterViewEntity) {
        nameTextView = findViewById(R.id.nameTextView)
        heightTextView = findViewById(R.id.heightTextView)
        homeWorldrecyclerView = findViewById(R.id.homeWorldRecyclerView)

        homeWorldrecyclerView.layoutManager = LinearLayoutManager(this)
        homeWorldrecyclerView.adapter = homeWorldItemAdapter
        homeWorldrecyclerView.isNestedScrollingEnabled = false;

        filmRecyclerView = findViewById(R.id.filmRecyclerView)

        filmRecyclerView.layoutManager = LinearLayoutManager(this)
        filmRecyclerView.adapter = filmAdapter
        filmRecyclerView.isNestedScrollingEnabled = false;
        updateCharacterDetail(viewEntity)
    }

    private fun updateCharacterDetail(entity: CharacterViewEntity) {
        entity.apply {
            nameTextView.text = title
            heightTextView.text = height
        }
        presenter.updateCharacterDetails(entity)
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        presenter.currentCharacterViewEntity()?.let { viewEntity ->
            savedInstanceState.putParcelable(CHARACTERS_KEY, viewEntity)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val viewEntity = savedInstanceState.getParcelable<CharacterViewEntity>(CHARACTERS_KEY)
        if (viewEntity != null) {
            updateCharacterDetail(viewEntity)
        } else {
            finish()
        }
    }

    companion object {
        const val CHARACTERS_KEY = "CHARACTERS_KEY"
    }

    override fun updateCharacterBackgroundDetails(viewEntityList: List<CharacterHomeWorldViewEntity>) {
        homeWorldItemAdapter.updateHomeWorldItem(viewEntityList)
        homeWorldItemAdapter.notifyDataSetChanged()
    }

    override fun updateCharacterFilmDetails(filmViewEntityList: List<FilmViewEntity>) {
        filmAdapter.updateCharacterItem(filmViewEntityList)
        filmAdapter.notifyDataSetChanged()
    }
}