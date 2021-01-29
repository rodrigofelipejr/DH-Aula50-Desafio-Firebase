package com.house.desafio_firebase.views.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.house.desafio_firebase.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        binding.imageViewSplash.alpha = 0f
        binding.imageViewSplash.animate().setDuration(1500).alpha(1f)

        binding.imageViewSplash.alpha = 0f
        binding.imageViewSplash.animate().setDuration(1500).alpha(1f).withEndAction {
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}