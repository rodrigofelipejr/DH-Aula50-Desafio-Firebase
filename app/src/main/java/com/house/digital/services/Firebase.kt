package com.house.digital.services

import com.google.firebase.firestore.FirebaseFirestore

//class Firebase {
//}

var db = FirebaseFirestore.getInstance()
var collectionGames = db.collection("games")