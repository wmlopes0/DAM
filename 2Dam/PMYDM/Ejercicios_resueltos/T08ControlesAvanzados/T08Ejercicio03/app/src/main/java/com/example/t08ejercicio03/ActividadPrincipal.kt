package com.example.t08ejercicio03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ActividadPrincipal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_principal)

        val botonComenzar: Button = findViewById(R.id.botonComenzar)
        val botonSalir: Button = findViewById(R.id.botonSalir)

        //Evento para el botón comenzar que pasa al siguiente activity
        botonComenzar.setOnClickListener{
            val intent: Intent = Intent(this,Pregunta1::class.java)
            startActivity(intent)
        }
        //Evento para el botón salir que finaliza el activity
        botonSalir.setOnClickListener {
            finish()
        }
    }
}