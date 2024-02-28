package com.example.pruebafirebase

data class Coche(
    val marca: String,
    val modelo: String,
    val numeroPuertas: Int,
    val velocidadMaxima: Int
) {
    //Constructor por defecto requerido por Firebase
    constructor() : this("", "", 0, 0)
}