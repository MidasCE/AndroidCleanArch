package com.example.app.main.view

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.app.R
import com.example.app.main.view.model.CharacterItemViewEntity
import com.example.app.main.view.presenter.CharacterListScreenPresenter
import com.google.android.material.snackbar.Snackbar
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : CharacterListScreenView, AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var presenter: CharacterListScreenPresenter

    @Inject
    lateinit var adapter: CharacterItemAdapter

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    private lateinit var recyclerView: RecyclerView
    private lateinit var loadingView: ProgressBar

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun initView() {
        recyclerView = findViewById(R.id.recyclerView)
        loadingView = findViewById(R.id.progressBar)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        (recyclerView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
    }

    override fun updateCharacterList(list: List<CharacterItemViewEntity>) {
        recyclerView.visibility = View.VISIBLE
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
}
