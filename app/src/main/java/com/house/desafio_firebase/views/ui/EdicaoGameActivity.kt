package com.house.desafio_firebase.views.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.house.desafio_firebase.databinding.ActivityEdicaoGameBinding
import com.house.desafio_firebase.entities.Game
import com.squareup.picasso.Picasso

class EdicaoGameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEdicaoGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEdicaoGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentGame = intent.getSerializableExtra("game") as Game

        binding.textViewTitleA.text = currentGame.name
        binding.textViewTitleB.text = currentGame.name
        binding.textViewCreatedAt.text = currentGame.releaseDate
        binding.textViewDescription.text = currentGame.description

        Picasso.get().load(currentGame.imageUrl)
            .into(binding.imageViewCover)
    }
}