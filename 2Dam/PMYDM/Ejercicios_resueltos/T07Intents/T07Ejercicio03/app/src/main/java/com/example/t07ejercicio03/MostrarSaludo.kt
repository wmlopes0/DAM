package com.example.t07ejercicio03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class MostrarSaludo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_saludo)
        //=================================================
        //Recupero el TextView resultado
        val resultado: TextView = findViewById(R.id.resultadoSaludo)

        //Recupero este intent
        val intentActual: Intent = intent

        //Controlo que existan los extras
        if (!intent.hasExtra(getString(R.string.nombre))) {
            Toast.makeText(applicationContext, R.string.toastExtraNulo, Toast.LENGTH_SHORT).show()
        }

        //Recupero los valores
        val nombre: String? = intent.getStringExtra(getString(R.string.nombre))

        //Muestro saludo
        resultado.setText("Hola $nombre")
    }
}