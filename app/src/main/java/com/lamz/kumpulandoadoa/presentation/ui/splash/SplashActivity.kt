package com.lamz.kumpulandoadoa.presentation.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.lamz.kumpulandoadoa.MainActivity
import com.lamz.kumpulandoadoa.R
import com.lamz.kumpulandoadoa.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
@Suppress("DEPRECATION")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        Handler().postDelayed( {
            startActivity(Intent(this, MainActivity::class.java))
            this@SplashActivity.overridePendingTransition(
                R.anim.slide_top,
                R.anim.slide_top
            )
            finish()
        }, 3000L)

    }
}