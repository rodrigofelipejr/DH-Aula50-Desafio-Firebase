package com.house.desafio_firebase.entities

import java.io.Serializable

data class Game(
    var name: String,
    var releaseDate: String,
    var description: String,
    var imageUrl: String
): Serializable{
    override fun toString(): String {
        return "Game(name='$name', releaseDate='$releaseDate', description='$description', imageUrl='$imageUrl')"
    }
}