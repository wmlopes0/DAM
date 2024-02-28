package com.example.t07ejercicio02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MostrarCalificaciones : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_calificaciones)
//        ===========================================================
        //Recupero el TextView resultado y el botón volver
        val resultado: TextView = findViewById(R.id.resultado)
        val botonVolver: Button = findViewById(R.id.botonVolver)

        //Recupero este intent
        val intentActual: Intent = intent

        //Controlo que existan los extras
        if (!intent.hasExtra(getString(R.string.PMYDM)) || !intent.hasExtra(getString(R.string.AD)) || !intent.hasExtra(getString(R.string.DI))) {
            Toast.makeText(applicationContext, R.string.toastExtraNulo, Toast.LENGTH_SHORT).show()
        }
        //Recupero los valores
        val pmydm: Float = intent.getFloatExtra(getString(R.string.PMYDM), 0.0f)
        val ad: Float = intent.getFloatExtra(getString(R.string.AD), 0.0f)
        val di: Float = intent.getFloatExtra(getString(R.string.DI), 0.0f)

        var media: Float = (pmydm + ad + di) / 3
        resultado.setText(media.toString())

        //Acción botón
        botonVolver.setOnClickListener {
            finish()
        }

    }
}