package com.house.desafio_firebase.views.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.house.desafio_firebase.databinding.ActivityHomeBinding
import com.house.desafio_firebase.viewmodels.GameViewModel
import com.house.desafio_firebase.views.adapter.GameAdapter

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var gameAdapter: GameAdapter
    private lateinit var gridLayoutManager: GridLayoutManager

    val viewModel by viewModels<GameViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return GameViewModel() as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gridLayoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.layoutManager = gridLayoutManager

        binding.recyclerView.hasFixedSize()

        viewModel.listGames.observe(this){
            gameAdapter = GameAdapter(binding.recyclerView.context, it)
            binding.recyclerView.adapter = gameAdapter
        }

        viewModel.getGames()

        binding.fabAddGame.setOnClickListener {
            finishAffinity()
            val intent = Intent(application, CadastroGameActivity::class.java)
            startActivity(intent)
        }
    }
}