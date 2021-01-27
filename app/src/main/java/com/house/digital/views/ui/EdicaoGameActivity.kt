package com.house.digital.views.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.house.digital.databinding.ActivityEdicaoGameBinding
import com.squareup.picasso.Picasso

class EdicaoGameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEdicaoGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEdicaoGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getSerializableExtra("title")
        val createdAt = intent.getSerializableExtra("createdAt")
        val description = intent.getSerializableExtra("description")
        val urlCover = intent.getSerializableExtra("urlCover")

        binding.textViewTitleA.text = title.toString()
        binding.textViewTitleB.text = title.toString()
        binding.textViewCreatedAt.text = createdAt.toString()
        binding.textViewDescription.text = description.toString()

        Picasso.get().load(urlCover.toString())
            .into(binding.imageViewCover)
    }
}