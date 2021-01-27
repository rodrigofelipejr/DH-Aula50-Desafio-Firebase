package com.house.digital.views.ui

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.house.digital.databinding.ActivityCadastroGameBinding
import com.house.digital.services.collectionGames
import dmax.dialog.SpotsDialog

class CadastroGameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroGameBinding
    private lateinit var cover: String

    lateinit var alertDialog: AlertDialog
    lateinit var storageReference: StorageReference
    private val CODE_IMG = 777

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        setListeners()
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

        binding.buttonCreateGame.setOnClickListener {
            var _game = getGame()
            saveGame(_game)
        }
    }

    private fun init() {
        alertDialog = SpotsDialog.Builder().setContext(this).build()
    }

    fun getGame(): MutableMap<String, Any> {
        val _game: MutableMap<String, Any> = HashMap()

        _game["title"] = binding.editTitle.text.toString()
        _game["createdAt"] = binding.editCreatedAt.text.toString()
        _game["description"] = binding.editDescription.text.toString()
        _game["urlCover"] = cover

        return _game
    }

    fun saveGame(game: MutableMap<String, Any>) {
        val title = binding.editTitle.text.toString()

        collectionGames.document(title).set(game).addOnSuccessListener {
            Log.d("ADD_NEW_GAME", "sendGame Firebase")

        }.addOnFailureListener {
            Log.i("ADD_NEW_GAME", it.toString())
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CODE_IMG) {

            storageReference = FirebaseStorage.getInstance().getReference(data.toString())
            val uploadTask = storageReference.putFile(data!!.data!!)

            uploadTask.continueWithTask { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "isSuccessfull", Toast.LENGTH_SHORT).show()
                }
                storageReference!!.downloadUrl
            }.addOnCompleteListener { task ->
                val downloadUri = task.result
                val url = downloadUri!!.toString()
                    .substring(0, downloadUri.toString().indexOf("&token"))

                Log.i("URL: ", url)
                cover = url

                alertDialog.dismiss()
            }
        }
    }
}