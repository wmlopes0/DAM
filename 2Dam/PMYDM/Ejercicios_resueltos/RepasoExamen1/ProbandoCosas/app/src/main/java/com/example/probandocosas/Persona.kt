package com.example.probandocosas

import android.util.Log

class Persona(var datos: DatosPersona) : InterfazPersona {
    override fun mostrarEdad() {
        Log.i("TAG", "Hola soy ${datos.nombre} y tengo ${datos.edad} años")
    }

}