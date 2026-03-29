package com.sample.todoapp.login_registration

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.sample.todoapp.R
import com.sample.todoapp.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val splashDelay: Long = 2500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lottie = findViewById<LottieAnimationView>(R.id.lottieAnimation)
        lottie.setAnimation("time_scedhuling.json")
        lottie.playAnimation()

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }, splashDelay)
    }
}