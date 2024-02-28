package com.example.ejercicio2

class Contacto(private var nombre: String, private var telefono: Int) {

    //Getter y Setter
    fun getNombre(): String {
        return nombre;
    }

    fun setNombre(nom: String) {
        nombre = nom
    }

    fun getTelefono(): Int {
        return telefono;
    }

    fun setTelefono(tel: Int) {
        telefono = tel
    }


}