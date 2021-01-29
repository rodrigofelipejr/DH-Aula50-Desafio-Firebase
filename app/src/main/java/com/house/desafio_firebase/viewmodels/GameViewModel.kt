package com.house.desafio_firebase.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.house.desafio_firebase.entities.Game
import com.house.desafio_firebase.services.collectionGames
import com.house.desafio_firebase.services.db
import kotlinx.coroutines.launch

class GameViewModel: ViewModel() {
    var listGames = MutableLiveData<ArrayList<Game>>()
    val response = MutableLiveData<String>()

    fun getGames() {
        viewModelScope.launch {
            collectionGames
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val gamesList = arrayListOf<Game>()
                        for (document in task.result!!) {
                            val game = Game(
                                document.data["name"] as String,
                                document.data["releaseDate"] as String,
                                document.data["description"] as String,
                                document.data["imageUrl"] as String
                            )
                            gamesList.add(game)
                            Log.i("GameViewModel", game.toString())
                        }
                        listGames.value = gamesList
                        Log.i("GameViewModel", listGames.value!!.size.toString())
                    } else {
                        Log.i("GameViewModel", "ERROR.", task.exception)
                    }
                }
        }
    }

    fun saveGame(game: Game) {
        viewModelScope.launch {
            val obj: MutableMap<String, Any> = HashMap()
            obj["name"] = game.name
            obj["releaseDate"] = game.releaseDate
            obj["description"] = game.description
            obj["imageUrl"] = game.imageUrl

            db.collection("games")
                .add(obj)
                .addOnSuccessListener { documentReference ->
                    response.value = "Success"
                }
                .addOnFailureListener { e ->
                    response.value = e.toString()
                }
        }
    }
}
