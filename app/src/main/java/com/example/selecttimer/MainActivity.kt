package com.example.selecttimer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val setButton = findViewById<Button>(R.id.setButton)
        setButton.setOnClickListener {
            setTimerNum()
        }
    }

    private fun setTimerNum(){
//        val hoursNum = findViewById<EditText>(R.id.hoursNum)
        val minutesNum = findViewById<EditText>(R.id.minutesNum)
        val secondsNum = findViewById<EditText>(R.id.secondsNum)

//        var hours: String = hoursNum.text.toString()
        var minutes: String = minutesNum.text.toString()
        var seconds: String = secondsNum.text.toString()

//        var millisTime = (hours.toInt() * 60 * 60 * 1000) + (minutes.toInt() * 60 * 1000) + (seconds.toInt() * 1000)
        var millisTime = (minutes.toInt() * 60 * 1000) + (seconds.toInt() * 1000)
        Log.i("milliseconds", millisTime.toString())


        var intent = Intent(this, SetTimerActivity::class.java)
        intent.putExtra("milliseconds", millisTime.toString())
        startActivity(intent)

    }

}