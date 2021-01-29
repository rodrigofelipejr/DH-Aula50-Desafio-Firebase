package com.house.desafio_firebase.views.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.house.desafio_firebase.databinding.ActivityCadastroBinding

class CadastroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textViewBack.setOnClickListener {
            finish()
        }
    }
}