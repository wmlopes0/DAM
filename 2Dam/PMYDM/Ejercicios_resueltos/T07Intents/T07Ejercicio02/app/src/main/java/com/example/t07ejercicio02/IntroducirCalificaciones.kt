package com.example.t07ejercicio02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class IntroducirCalificaciones : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        ===========================================
        //Recupero botón
        val botonMedia: Button = findViewById(R.id.notaMedia)
        //Recupero editText
        val editTextPmydm: EditText = findViewById(R.id.inputPMYDM)
        val editTextAd: EditText = findViewById(R.id.inputAD)
        val editTextDi: EditText = findViewById(R.id.inputDI)

        //Acción botón
        botonMedia.setOnClickListener {
            //Recupero valores
            val pmydm: Float? = editTextPmydm.text.toString().toFloatOrNull()
            val ad: Float? = editTextAd.text.toString().toFloatOrNull()
            val di: Float? = editTextDi.text.toString().toFloatOrNull()
            //Controlo que haya valores
            if (pmydm == null || ad == null || di == null) {
                Toast.makeText(applicationContext, R.string.toastValoresNulos, Toast.LENGTH_SHORT)
                    .show()
            } else {
                //Creo intent
                val intentMostrarCalificaciones: Intent = Intent(this, MostrarCalificaciones::class.java)
                //Paso variables
                intentMostrarCalificaciones.putExtra(getString(R.string.PMYDM), pmydm)
                intentMostrarCalificaciones.putExtra(getString(R.string.AD), ad)
                intentMostrarCalificaciones.putExtra(getString(R.string.DI), di)
                //Inicio intent
                startActivity(intentMostrarCalificaciones)
            }
        }
    }
}