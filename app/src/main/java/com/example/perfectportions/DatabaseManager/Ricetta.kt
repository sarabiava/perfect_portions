package com.example.perfectportions.DatabaseManager

data class Ricetta (
    val id: Int = 0,
    val nome: String,
    val ingredienti: List<String>,
    val image_path: String,
    val note: String
)