package com.example.kotlinlotteryapp

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class SecondActivity : AppCompatActivity() {
    private lateinit var shareButton: Button
    private lateinit var lotteryNumbers: TextView
    private lateinit var kotlinImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

        kotlinImage = findViewById(R.id.kotlinImageView)
        shareButton = findViewById(R.id.shareButton)
        lotteryNumbers = findViewById(R.id.lotteryNumbers)

        val rotationAnimator = ObjectAnimator.ofFloat(kotlinImage, "rotation", 0f, 360f)
        rotationAnimator.duration = 1000
        rotationAnimator.repeatCount = ObjectAnimator.INFINITE
        rotationAnimator.start()

        val textColorAnimator = ValueAnimator.ofArgb(
            ContextCompat.getColor(this, android.R.color.white),
            ContextCompat.getColor(this, android.R.color.black)
        )
        textColorAnimator.duration = 1000
        textColorAnimator.repeatCount = ValueAnimator.INFINITE
        textColorAnimator.addUpdateListener { animator ->
            val color = animator.animatedValue as Int
            lotteryNumbers.setTextColor(color)
        }
        textColorAnimator.start()

        val randomNumbers: String = generateRandomNumbers()
        lotteryNumbers.text = randomNumbers

        val username = receiveUsername()



        shareButton.setOnClickListener {
            shareResult(username, randomNumbers)
        }
    }

    private fun generateRandomNumbers(): String {
        // Create list of random numbers
        val randomNumbers = List(6) {
            /*
            lambda expression to generate random numbers
            between 1 and 49
             */
            (1..49).random()

            // Using Java util's Random!
//            val random = Random()
//            val randomNumber = random.nextInt(50)
        }
        return randomNumbers.joinToString(" ")
    }

    private fun receiveUsername(): String {
        // Retrieve the extras that were added to an INTENT
        val bundle: Bundle? = intent.extras

        return bundle?.getString("username").toString()
    }

    private fun shareResult(username: String, generatedNumbers: String) {
        // Implicit Intent
        val i = Intent(Intent.ACTION_SEND)

        i.setType("text/plain")
        i.putExtra(Intent.EXTRA_SUBJECT, "$username generated these lottery numbers")
        i.putExtra(Intent.EXTRA_TEXT, "The lottery numbers are: $generatedNumbers")
        startActivity(i)
    }
}