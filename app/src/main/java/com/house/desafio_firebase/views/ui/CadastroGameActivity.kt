package com.house.desafio_firebase.views.ui

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.house.desafio_firebase.databinding.ActivityCadastroGameBinding
import com.house.desafio_firebase.entities.Game
import com.house.desafio_firebase.viewmodels.GameViewModel
import com.squareup.picasso.Picasso
import dmax.dialog.SpotsDialog

class CadastroGameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroGameBinding
    private lateinit var cover: String

    lateinit var alertDialog: AlertDialog
    lateinit var storageReference: StorageReference
    private val CODE_IMG = 777

    val viewModel by viewModels<GameViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return GameViewModel() as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        setListeners()

        var gameExtra = intent.getSerializableExtra("game") as? Game

        if (gameExtra != null) {
            binding.editName.setText(gameExtra.name)
            binding.editReleaseDate.setText(gameExtra.releaseDate)
            binding.editDescription.setText(gameExtra.description)

            Picasso.get().load(gameExtra.imageUrl)
                .into(binding.imageViewRounded)

            cover = gameExtra.imageUrl

            binding.buttonCreateGame.setOnClickListener {
                var game = getGame()
                game.id = gameExtra.id
                viewModel.updateGame(game)
                toMain()
            }
        } else {
            binding.buttonCreateGame.setOnClickListener {
                var game = getGame()
                viewModel.saveGame(game)
                toMain()
            }
        }
    }

    private fun setListeners() {
        binding.textViewBack.setOnClickListener {
            super.finish()
        }

        binding.cardViewImage.setOnClickListener {
            val intent = Intent()
            intent.type = "image/"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Selecionar imagem"), CODE_IMG)
        }
    }

    private fun init() {
        alertDialog = SpotsDialog.Builder().setContext(this).build()
    }

    fun getGame(): Game {
        return Game(
            name = binding.editName.text.toString(),
            releaseDate = binding.editReleaseDate.text.toString(),
            description = binding.editDescription.text.toString(),
            imageUrl = cover,
        )
    }

    fun toMain() {
        finishAffinity()
        var intent = Intent(application, HomeActivity::class.java)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CODE_IMG) {

            storageReference = FirebaseStorage.getInstance().getReference(data.toString())
            val uploadTask = storageReference.putFile(data!!.data!!)

            uploadTask.continueWithTask { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Imagem anexada", Toast.LENGTH_SHORT).show()
                }
                storageReference!!.downloadUrl
            }.addOnCompleteListener { task ->
                val downloadUri = task.result
                val url = downloadUri!!.toString()
                    .substring(0, downloadUri.toString().indexOf("&token"))

                Log.i("URL: ", url)
                cover = url

                binding.imageViewRounded.getLayoutParams().height = 200;

                Picasso.get().load(url)
                    .into(binding.imageViewRounded)

                alertDialog.dismiss()
            }
        }
    }
}