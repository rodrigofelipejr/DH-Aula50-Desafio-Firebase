package com.house.desafio_firebase.views.ui

import android.content.Intent
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

        binding.textViewNameA.text = currentGame.name
        binding.textViewNameB.text = currentGame.name
        binding.textViewReleaseDate.text = currentGame.releaseDate
        binding.textViewDescription.text = currentGame.description

        Picasso.get().load(currentGame.imageUrl)
            .into(binding.imageViewCover)

        binding.fabEdit.setOnClickListener{
            finishAffinity()
            val intent = Intent(it.context, CadastroGameActivity::class.java)
            intent.putExtra("game", currentGame)
            it.context.startActivity(intent)
        }
    }
}