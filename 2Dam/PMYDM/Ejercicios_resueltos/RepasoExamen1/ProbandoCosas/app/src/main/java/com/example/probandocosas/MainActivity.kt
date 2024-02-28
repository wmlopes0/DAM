package com.example.probandocosas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.lang.IndexOutOfBoundsException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Clases,interfaces y Dataclass
        var datosPersona: DatosPersona = DatosPersona("Walter", 25)
        val persona1: Persona = Persona(datosPersona)
        persona1.mostrarEdad()

        //When y sus cosas
        var lenguaje: String = "Kotlin"

        when {
            lenguaje.endsWith("in") -> {
                Log.i("EJERCICIO", "Kotlin")
            }

            lenguaje.endsWith("o") -> {
                Log.i("EJERCICIO", "No es Kotlin")
            }
        }

        //Listas jiji
        var listaInmutable: List<Int> = listOf(1, 2, 3, 4, 5, 6)
        var listaMutable: ArrayList<Int> = arrayListOf(1, 2, 3, 4, 5, 6)

        listaInmutable.forEach { numero ->
            Log.e("MUESTROLISTA", numero.toString())
        }

        for (i in listaMutable) {
            Log.e("MUESTROLISTA", i.toString())
        }



    }


}