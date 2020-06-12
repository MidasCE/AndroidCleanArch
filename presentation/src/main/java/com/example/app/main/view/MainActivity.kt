package com.example.app.main.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.app.R
import com.example.app.character.details.view.CharacterDetailsActivity
import com.example.app.character.details.view.CharacterDetailsActivity.Companion.CHARACTERS_KEY
import com.example.app.main.view.model.CharacterViewEntity
import com.example.app.main.view.presenter.CharacterListScreenPresenter
import com.google.android.material.snackbar.Snackbar
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class MainActivity : CharacterListScreenView, AppCompatActivity(), HasAndroidInjector, CharacterItemAdapter.CharacterItemInteractionListener {

    @Inject
    lateinit var presenter: CharacterListScreenPresenter

    @Inject
    lateinit var adapter: CharacterItemAdapter

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    private lateinit var recyclerView: RecyclerView
    private lateinit var loadingView: ProgressBar
    private lateinit var searchEditText: EditText

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        recyclerView = findViewById(R.id.recyclerView)
        loadingView = findViewById(R.id.progressBar)
        searchEditText = findViewById(R.id.searchInput)
        searchEditText.addTextChangedListener(object : TextWatcher {

            @Synchronized
            override fun afterTextChanged(e: Editable?) {

            }

            override fun beforeTextChanged(c: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            @Synchronized
            override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
                charSequence?.let { chars ->
                    if (chars.isEmpty()) {
                        presenter.fetchCharacterList()
                    } else {
                        presenter.searchCharacters(chars.toString())
                    }
                }
            }
        })

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        (recyclerView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE && searchEditText.text.isEmpty()) {
                    presenter.fetchCharacterList()
                }
            }
        })
        presenter.fetchCharacterList()
    }

    override fun addCharacterList(list: List<CharacterViewEntity>) {
        adapter.addCharacterItem(list)
        adapter.notifyDataSetChanged()
    }

    override fun updateCharacterList(list: List<CharacterViewEntity>) {
        adapter.updateCharacterItem(list)
        adapter.notifyDataSetChanged()
    }

    override fun showLoading() {
        loadingView.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loadingView.visibility = View.GONE
    }

    override fun showError() {
        Snackbar.make( findViewById(android.R.id.content), "There is an error. Please try again later", Snackbar.LENGTH_LONG).show()
    }

    override fun onCharacterItemClick(characterViewEntity: CharacterViewEntity) {
        val intent = Intent(this, CharacterDetailsActivity::class.java)
        intent.putExtra(CHARACTERS_KEY, characterViewEntity)
        startActivity(intent)
    }

}
