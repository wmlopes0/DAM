package com.example.t07ejercicio03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class EscribeSaludo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        ===========================================
        //Recupero TextEdit con el nombre del usuario
        val editTextNombreUsuario: EditText = findViewById(R.id.editTextNombre)
        //Recupero el boton
        val botonSaludo: Button = findViewById(R.id.botonHola)

        //Acción botón
        botonSaludo.setOnClickListener {
            //Recupero el nombre
            val nombreUsuario: String = editTextNombreUsuario.text.toString()
            //Controlo que no sea nulo
            if (nombreUsuario == "") {
                Toast.makeText(applicationContext, R.string.toastValorNulo, Toast.LENGTH_SHORT)
                    .show()
            } else {
                //Creo intent
                val intentMostrarSaludo: Intent = Intent(this, MostrarSaludo::class.java)
                //Paso parámetro
                intentMostrarSaludo.putExtra(getString(R.string.nombre), nombreUsuario)
                //Inicio el intent
                startActivity(intentMostrarSaludo)
            }
        }
    }
}