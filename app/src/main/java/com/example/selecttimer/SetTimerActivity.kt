package com.example.selecttimer

import android.media.AudioManager
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SetTimerActivity : AppCompatActivity() {
    private lateinit var soundPool: SoundPool
    private var soundResId = 0

    inner class MyCountDownTimer(millisInFuture: Long, countDownInterval: Long) : CountDownTimer(millisInFuture, countDownInterval) {

        var isRunning = false
        val timerText : TextView = findViewById(R.id.timerText)

        override fun onTick(millisUntilFinished: Long) {
            val minute = millisUntilFinished / 1000L / 60L
            val second = millisUntilFinished / 1000L % 60L
            timerText.text = "%1d:%2$02d".format(minute, second)
        }

        override fun onFinish() {
            timerText.text = "0:00"
            soundPool.play(soundResId, 1.0f, 100f, 0, 0, 1.0f)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_timer)

        val milliSeconds = intent.getStringExtra("milliseconds")?.toLong()

        val timerText : TextView = findViewById(R.id.timerText)
        val playStop = findViewById<FloatingActionButton>(R.id.playStop)

        if (milliSeconds != null) {
            setTimerText(milliSeconds)
        }
//        val timer = milliSeconds?.toLong()?.let { MyCountDownTimer(it, 100) }
        val timer = milliSeconds?.let { MyCountDownTimer(it, 100) }

        playStop.setOnClickListener {
            if (timer != null) {
                timer.isRunning = when (timer.isRunning) {
                    true -> {
                        timer.cancel()
                        playStop.setImageResource(
                            R.drawable.ic_baseline_play_arrow_24
                        )
                        false
                    }
                    false -> {
                        timer.start()
                        playStop.setImageResource(
                            R.drawable.ic_baseline_stop_24
                        )
                        true
                    }
                }
            }
        }

    }

    override fun onResume() {
        super.onResume()
        soundPool = SoundPool(2, AudioManager.STREAM_ALARM, 0)
        soundResId = soundPool.load(this, R.raw.bellsound, 1)
    }

    override fun onPause() {
        super.onPause()
        soundPool.release()
    }

    private fun setTimerText(millisseconds: Long) {
        val timerText : TextView = findViewById(R.id.timerText)
        val minute = millisseconds / 1000L / 60L
        val second = millisseconds / 1000L % 60L
        timerText.text = "%1d:%2$02d".format(minute, second)
    }
}