package com.house.desafio_firebase.services

import com.google.firebase.firestore.FirebaseFirestore

var db = FirebaseFirestore.getInstance()
var collectionGames = db.collection("games")