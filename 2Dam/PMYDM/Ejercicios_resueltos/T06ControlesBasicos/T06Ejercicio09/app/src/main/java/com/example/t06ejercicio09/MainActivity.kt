package com.example.t06ejercicio09

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var botonSup: Button
    private lateinit var botonMed: Button
    private lateinit var botonInf: Button
    private lateinit var botonToast: Button
    private lateinit var titulo: TextView
    private lateinit var textViewcontador: TextView
    private var contador: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Recupero componentes
        botonSup = findViewById(R.id.sup)
        botonMed = findViewById(R.id.med)
        botonInf = findViewById(R.id.inf)
        botonToast = findViewById(R.id.botonToast)
        titulo = findViewById(R.id.textViewTitulo)
        textViewcontador = findViewById(R.id.textViewContador)

        //BOTON TOAST
        botonToast.setOnClickListener {
            Toast.makeText(this, R.string.toast, Toast.LENGTH_SHORT).show()
        }

        //BOTONES
        establecerListener(botonSup)
        establecerListener(botonMed)
        establecerListener(botonInf)
    }

    private fun establecerListener(boton: Button) {
        boton.setOnClickListener {
            contador++
            var textoContador: String = "Contador: $contador"
            textViewcontador.setText(textoContador)
            var textoTitulo = "Último: "
            when (boton.text) {
                getString(R.string.sup) -> {
                    textoTitulo += "Superior"
                    titulo.setText(textoTitulo)
                }

                getString(R.string.med) -> {
                    textoTitulo += "Medio"
                    titulo.setText(textoTitulo)
                }

                getString(R.string.inf) -> {
                    textoTitulo += "Inferior"
                    titulo.setText(textoTitulo)
                }
            }
        }
    }
}