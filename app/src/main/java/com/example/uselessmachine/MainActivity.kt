package com.example.uselessmachine

import android.graphics.Color
import android.graphics.Color.rgb
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.CompoundButton
import android.widget.Toast
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast.makeText as toastMakeText

class MainActivity() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //lambda is impenitent the one function without mentioning it
        button_main_lookProductive.setOnClickListener {
            Toast.makeText(this, "You are doing something ${(it as Button).text.toString()}",
                Toast.LENGTH_SHORT).show()
        }


        switch_main_useless.setOnCheckedChangeListener { CompoundButton, isChecked ->
            val message = if(isChecked) "Switch is ON" else "Switch is OFF"
            val time = (1000..5000).random().toLong()
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            Toast.makeText(this, "The button is: ${isChecked.toString()}", Toast.LENGTH_SHORT).show()
            if (isChecked){

                val uncheckTimer = object : CountDownTimer(time, 1000){

                    override fun onFinish() {
                        switch_main_useless.isChecked = false
                        layout_main.setBackgroundColor(Color.rgb((0..255).random(),
                            (0..255).random(),(0..255).random()))
                    }

                    override fun onTick(millisRemaining: Long) {
                        if(!switch_main_useless.isChecked){
                            cancel()
                        }
                        //can be empty
                    }
                }
                uncheckTimer.start()
                }
            }


        button_main_selfDistruct.setOnClickListener{
            Toast.makeText(this, "Count down to self destruct initated", Toast.LENGTH_SHORT)

            val countToDestruct = object : CountDownTimer(10000, 250){

                private var isRed = false
                private var isBlack = false
                private var timeRemaining = 10000
                private var btwFlashes = 1000
                private var ticks = 0

                override fun onFinish() {
                    layout_main.setBackgroundColor(Color.rgb(255,
                        0,0))
                    button_main_selfDistruct.text = "BOOOOOOOOM!!!!!!"
                    finish()
                }

                override fun onTick(millisUntilFinished : Long) {
                    ticks++
                    button_main_selfDistruct.text = ((millisUntilFinished)/1000).toString()

                    if(ticks % 12 == 0){ //after 12 ticks make time bwt flashes smaller
                        btwFlashes/=2
                    }

                    if(timeRemaining % btwFlashes == 0) {

                        if (!isRed) {
                            layout_main.setBackgroundColor(
                                Color.rgb(255, 0, 0)
                            )
                            isRed = true
                        }
                        else {
                            if(!isBlack){
                                layout_main.setBackgroundColor(
                                    Color.rgb(0, 0, 0)
                                )
                                isBlack = true
                            }
                            else {
                                layout_main.setBackgroundColor(
                                    Color.rgb(255,255, 255
                                    )
                                )
                                isBlack = false
                            }
                            isRed = false
                        }

                    }
                    timeRemaining -= 250
                }

            }
            countToDestruct.start()
        }

        /*progressBar_main_lookProductive.setOnClickListener{
            progressBar_main_lookProductive.isVisible
            progressBar_main_lookProductive.progress = 0
            val countProgress = object : CountDownTimer (1000, 100){
                override fun onFinish() {
                    progressBar_main_lookProductive.visibility = false

                }

                override fun onTick(millisRemaining: Long) {
                    progressBar_main_lookProductive.incrementProgressBy(10)
                }
            }

        }*/
    }
}



