package com.example.app.main.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.media.audiofx.Visualizer
import android.media.audiofx.Visualizer.OnDataCaptureListener
import android.util.AttributeSet
import android.view.View
import kotlin.math.abs
import kotlin.math.ceil

class BarVisualizer @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var bytes: ByteArray? = null
    private var paint: Paint = Paint()
    private var visualizer: Visualizer? = null
    private var color: Int = Color.RED
    private var density: Int = 100
    private var gap: Int = 4

    init {
        paint.style = Paint.Style.FILL
        paint.color = color
    }

    override fun onDraw(canvas: Canvas) {
        bytes?.let { byteArray ->
            val barWidth = (width / density).toFloat()
            val div = (byteArray.size / density).toFloat()
            paint.strokeWidth = barWidth - gap
            for (i in 0 until density) {
                val bytePosition = ceil(i * div.toDouble()).toInt()
                val top = height +
                        (abs(byteArray[bytePosition].toDouble()) + 128).toByte() * height / 128
                val barX = i * barWidth + barWidth / 2
                canvas.drawLine(barX, height.toFloat(), barX, top.toFloat(), paint)
            }
            super.onDraw(canvas)
        }
    }

    fun setPlayer(audioSessionId: Int) {
        visualizer = Visualizer(audioSessionId)
        visualizer?.apply {
            enabled = false
            captureSize = 4096 * 2
            setDataCaptureListener(object : OnDataCaptureListener {
                override fun onWaveFormDataCapture(
                    visualizer: Visualizer, bytes: ByteArray,
                    samplingRate: Int
                ) {
                    this@BarVisualizer.bytes = bytes
                    invalidate()
                }

                override fun onFftDataCapture(
                    visualizer: Visualizer, bytes: ByteArray,
                    samplingRate: Int
                ) {
                }
            }, Visualizer.getMaxCaptureRate(), true, false)
            enabled = true
        }
    }

    fun release() {
        visualizer?.let {
            it.release()
            bytes = null
            invalidate()
        }
    }

}