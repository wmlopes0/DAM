package com.example.t09ejercicio1.modelo

data class Player(
    val first_name: String,
    val height_feet: Int,
    val height_inches: Int,
    val id: Int,
    val last_name: String,
    val position: String,
    val team: Team,
    val weight_pounds: Int
)