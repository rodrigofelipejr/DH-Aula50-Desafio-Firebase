package com.house.digital.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.house.digital.entities.Game
import com.house.digital.services.collectionGames

class GameViewModel : ViewModel() {
    var listGames = MutableLiveData<ArrayList<Game>>()

    fun getListGames() {
        var _list = ArrayList<Game>()
        collectionGames.get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        _list.add(
                            Game(
                                document.data["title"].toString(),
                                document.data["createdAt"].toString(),
                                document.data["description"].toString(),
                                document.data["urlCover"].toString()
                            )
                        )
                    }
                    listGames.value = _list
                } else {
                    Log.i("GAMEVIEWMODEL", "ERROR: ", task.exception)
                }
            }
    }
}
