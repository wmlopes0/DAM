package com.example.probandocontroles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class Activity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        val intent: Intent = intent
        val extras: Bundle? = intent.extras

        if (extras == null) {
            Toast.makeText(applicationContext, "NO HAY EXTRAS", Toast.LENGTH_SHORT).show()
        } else {
            var nombre: String? = intent.getStringExtra("Nombre")
            var edad: Int = intent.getIntExtra("Edad",0)
            Toast.makeText(applicationContext, "HOLA $nombre TIENES $edad AÑOS", Toast.LENGTH_SHORT).show()
        }
    }
}