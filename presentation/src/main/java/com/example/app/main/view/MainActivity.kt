package com.example.app.main.view

import android.Manifest
import android.R.attr.duration
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.app.R
import com.example.app.main.view.presenter.MainScreenPresenter
import com.google.android.material.snackbar.Snackbar
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class MainActivity : MainView, AppCompatActivity(), HasAndroidInjector {

    companion object {
        const val REQ_CODE_PICK_SOUND_FILE = 1
        const val REQUEST_CODE_ASK_PERMISSIONS = 2
        const val INTERVAL = 32L
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var presenter: MainScreenPresenter

    private lateinit var pickSongButton: Button
    private lateinit var playButton: Button
    private lateinit var pauseButton: Button
    private lateinit var barVisualizer: SoundVisualizerBarView
    private lateinit var player: MediaPlayer

    private var handler: Handler? = Handler()

    private var runnable = object : Runnable {
        override fun run() {
            player.currentPosition
            barVisualizer.updatePlayerPercent(player.currentPosition / player.duration.toFloat())
            if (player.isPlaying) {
                handler?.postDelayed(this, INTERVAL);
            }
        }
    };

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handler = Handler()
        player = MediaPlayer()
        player.isLooping = false

        barVisualizer = findViewById(R.id.visualizer)
        pickSongButton = findViewById(R.id.pickSongButton)
        playButton = findViewById(R.id.playButton)
        pauseButton = findViewById(R.id.pauseButton)
        playButton.setOnClickListener {
            player.start()
            runnable.run()
            pickSongButton.visibility = GONE
        }
        pauseButton.setOnClickListener {
            player.pause()
            pickSongButton.visibility = VISIBLE
        }
        pickSongButton.setOnClickListener {
            presenter.onClickPickSong()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQ_CODE_PICK_SOUND_FILE) {
            if (resultCode == Activity.RESULT_OK) { //the selected audio.
                val uri: Uri? = data?.data
                uri?.let {
                    presenter.onSongPicked(it)
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun navigateToRecordAudioPermissionSettings() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.RECORD_AUDIO
            ),
            REQUEST_CODE_ASK_PERMISSIONS
        )

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE_ASK_PERMISSIONS -> {
                // If request is cancelled, the result arrays are empty.
                presenter.onReceivedLocationPermissionResponse(
                    (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                )
                return
            }
        }
    }

    override fun showMessage(message: String) {
        Snackbar.make(findViewById(R.id.rootView), message, Snackbar.LENGTH_SHORT).show()
    }

    private fun showMediaButton() {
        playButton.visibility = VISIBLE
        pauseButton.visibility = VISIBLE
    }

    override fun navigatePickSongScreen() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "audio/mpeg"
        startActivityForResult(
            Intent.createChooser(
                intent,
                "Select audio file"
            ), REQ_CODE_PICK_SOUND_FILE
        )
    }

    override fun setUpVisualizer(uri: Uri) {
        handler?.removeCallbacks(runnable)
        player.reset()
        player.setDataSource(this, uri)

        player.prepareAsync()
        player.setOnPreparedListener { player ->
            showMediaButton()
            pickSongButton.visibility = GONE
            barVisualizer.updateVisualizer(uri)
            barVisualizer.updatePlayerPercent(0F)
        }
    }

    override fun onStop() {
        super.onStop()
        if (player.isPlaying) {
            player.stop()
            player.release()
        }
    }
}
