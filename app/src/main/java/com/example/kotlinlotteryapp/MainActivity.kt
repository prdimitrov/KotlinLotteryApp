package com.example.kotlinlotteryapp

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private lateinit var kotlinImage: ImageView
    private lateinit var usernameInput: EditText
    private lateinit var getNumbersBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        kotlinImage = findViewById(R.id.kotlinImageView)
        usernameInput = findViewById(R.id.usernameEditText)

        val rotationAnimator = ObjectAnimator.ofFloat(kotlinImage, "rotation", 0f, 360f)
        rotationAnimator.duration = 1000
        rotationAnimator.repeatCount = ObjectAnimator.INFINITE
        rotationAnimator.start()

        val hintColorAnimator = ValueAnimator.ofArgb(
            ContextCompat.getColor(this, android.R.color.white),
            ContextCompat.getColor(this, android.R.color.black)
        )
        hintColorAnimator.duration = 1000
        hintColorAnimator.repeatCount = ValueAnimator.INFINITE
        hintColorAnimator.addUpdateListener { animator ->
            val color = animator.animatedValue as Int
            usernameInput.setHintTextColor(color)
        }
        hintColorAnimator.start()

    }
}