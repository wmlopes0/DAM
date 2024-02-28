package com.example.t07ejercicio01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class Correr : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Toast para mostrar el estado
//        Toast.makeText(applicationContext,R.string.toastOnCreate,Toast.LENGTH_SHORT).show()
        //Recupero el botón
        val botonHacerFlexiones: Button = findViewById(R.id.hacerFlexiones)

        //Acción botón
        botonHacerFlexiones.setOnClickListener {
            //Creo intent
            val intentHacerFlexiones: Intent = Intent(this, HacerFlexiones::class.java)
            //Inicio el intent
            startActivity(intentHacerFlexiones)
        }
    }

}