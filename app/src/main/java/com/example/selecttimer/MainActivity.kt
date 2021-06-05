package com.example.selecttimer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.selecttimer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//        setContentView(R.layout.activity_main)

        val setButton = findViewById<Button>(R.id.setButton)
        setButton.setOnClickListener {
            setTimerNum()
        }
    }

    private fun setTimerNum(){
//        val hoursNum = findViewById<EditText>(R.id.hoursNum)
//        val minutesNum = findViewById<EditText>(R.id.minutesNum)
//        val secondsNum = findViewById<EditText>(R.id.secondsNum)

//        var hours: String = hoursNum.text.toString()
        var minutes: String = binding.minutesNum.text.toString()
        var seconds: String = binding.secondsNum.text.toString()

//        var millisTime = (hours.toInt() * 60 * 60 * 1000) + (minutes.toInt() * 60 * 1000) + (seconds.toInt() * 1000)
        var millisTime = (minutes.toInt() * 60 * 1000) + (seconds.toInt() * 1000)
        Log.i("milliseconds", millisTime.toString())


        var intent = Intent(this, SetTimerActivity::class.java)
        intent.putExtra("milliseconds", millisTime.toString())
        startActivity(intent)

    }

}