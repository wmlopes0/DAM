package com.example.t08ejercicio05

class Pelicula(
    private var imagen: Int,
    private var titulo: String,
    private var direccionWeb: String
) {
    fun setImagen(img: Int) {
        imagen = img
    }

    fun getImagen(): Int {
        return imagen
    }

    fun setTitulo(tit: String) {
        titulo = tit
    }

    fun getTitulo(): String {
        return titulo
    }

    fun setDireccionWeb(dirWeb: String) {
        direccionWeb = dirWeb
    }

    fun getDireccionWeb(): String {
        return direccionWeb
    }
}