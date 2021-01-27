package com.house.digital.views.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.house.digital.databinding.ActivityMainBinding
import com.house.digital.viewmodels.GameViewModel
import com.house.digital.views.adapter.GameAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    val viewModel by viewModels<GameViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return GameViewModel() as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        viewModel.getListGames()

        viewModel.listGames.observe(this) {
            recyclerView.adapter = GameAdapter(it, this)
        }

        binding.fabAddGame.setOnClickListener {
            var intent = Intent(application, CadastroGameActivity::class.java)
            startActivity(intent)
        }
    }
}