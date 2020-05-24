package com.example.app.main.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.net.Uri
import android.util.AttributeSet
import android.view.View
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream
import kotlin.experimental.and
import kotlin.experimental.or
import kotlin.math.ceil

class SoundVisualizerBarView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    companion object {
        const val VISUALIZER_HEIGHT = 70
    }

    /**
     * bytes array converted from file.
     */
    private var bytes: ByteArray? = null

    /**
     * Percentage of audio sample scale
     * Should updated dynamically while audioPlayer is played
     */
    private var denseness = 0f

    /**
     * Canvas painting for sample scale, filling played part of audio sample
     */
    private val playedStatePainting: Paint = Paint()
    /**
     * Canvas painting for sample scale, filling not played part of audio sample
     */
    private val notPlayedStatePainting: Paint = Paint()

    private var w = 0
    private var h = 0

    private var playedStateColor = 0xFFFB8C00.toInt()
    private var nonPlayedStateColor = Color.DKGRAY

    init {
        bytes = null
        playedStatePainting.strokeWidth = 1f
        playedStatePainting.isAntiAlias = true
        playedStatePainting.color = playedStateColor
        notPlayedStatePainting.strokeWidth = 1f
        notPlayedStatePainting.isAntiAlias = true
        notPlayedStatePainting.color = nonPlayedStateColor
    }

    /**
     * update and redraw Visualizer view
     */
    @Throws(FileNotFoundException::class)
    fun updateVisualizer(uri: Uri) {
        val inputStream = context.contentResolver.openInputStream(uri)
        inputStream?.let {
            bytes = readInputStream(it)
            invalidate()
        }
    }

    /**
     * Update player percent. 0 - file not played, 1 - full played
     *
     * @param percent
     */
    fun updatePlayerPercent(percent: Float) {
        denseness = ceil(width * percent.toDouble()).toFloat()
        if (denseness < 0) {
            denseness = 0f
        } else if (denseness > width) {
            denseness = width.toFloat()
        }
        invalidate()
    }

    override fun onLayout(
        changed: Boolean,
        left: Int,
        top: Int,
        right: Int,
        bottom: Int
    ) {
        super.onLayout(changed, left, top, right, bottom)
        w = measuredWidth
        h = measuredHeight
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (width == 0) {
            return
        }
        bytes?.let { byteArray ->
            val totalBarsCount = width / dp(3f).toFloat()
            if (totalBarsCount <= 0.1f) {
                return
            }
            var value: Byte
            val samplesCount = byteArray.size * 8 / 5
            val samplesPerBar = samplesCount / totalBarsCount
            var barCounter = 0f
            var nextBarNum = 0
            val y = (height - dp(VISUALIZER_HEIGHT.toFloat())) / 2
            var barNum = 0
            var lastBarNum: Int
            var drawBarCount: Int
            for (a in 0 until samplesCount) {
                if (a != nextBarNum) {
                    continue
                }
                drawBarCount = 0
                lastBarNum = nextBarNum
                while (lastBarNum == nextBarNum) {
                    barCounter += samplesPerBar
                    nextBarNum = barCounter.toInt()
                    drawBarCount++
                }
                val bitPointer = a * 5
                val byteNum = bitPointer / java.lang.Byte.SIZE
                val byteBitOffset = bitPointer - byteNum * java.lang.Byte.SIZE
                val currentByteCount = java.lang.Byte.SIZE - byteBitOffset
                val nextByteRest = 5 - currentByteCount
                value = ((byteArray[byteNum]).toInt() shr byteBitOffset and (2 shl Math.min(
                    5,
                    currentByteCount
                ) - 1) - 1).toByte()
                if (nextByteRest > 0) {
                    value = (value.toInt() shl nextByteRest).toByte()
                    value =
                        value or bytes!![byteNum + 1] and ((2 shl nextByteRest - 1) - 1).toByte()
                }
                for (b in 0 until drawBarCount) {
                    val left = barNum * dp(4f).toFloat()
                    val top = y + dp(
                        VISUALIZER_HEIGHT - Math.max(
                            1f,
                            VISUALIZER_HEIGHT * value / 31.0f
                        )
                    ).toFloat()
                    val right = left + dp(3f)
                    val bottom = y + dp(VISUALIZER_HEIGHT.toFloat()).toFloat()
                    if (left < denseness && left + dp(2f) < denseness) {
                        canvas.drawRect(left, top, right, bottom, playedStatePainting)
                    } else {
                        canvas.drawRect(left, top, right, bottom, notPlayedStatePainting)
                        if (left < denseness) {
                            canvas.drawRect(left, top, right, bottom, playedStatePainting)
                        }
                    }
                    barNum++
                }
            }
        }
    }

    private fun dp(value: Float): Int {
        return if (value == 0f) {
            0
        } else ceil(context.resources.displayMetrics.density * value).toInt()
    }

    private fun readInputStream(inputStream: InputStream): ByteArray? {
        val outputStream = ByteArrayOutputStream()
        val buf = ByteArray(1024)
        var len: Int
        try {
            while (inputStream.read(buf).also { len = it } != -1) {
                outputStream.write(buf, 0, len)
            }
            outputStream.close()
            inputStream.close()
        } catch (e: IOException) {
        }
        return outputStream.toByteArray()
    }
}