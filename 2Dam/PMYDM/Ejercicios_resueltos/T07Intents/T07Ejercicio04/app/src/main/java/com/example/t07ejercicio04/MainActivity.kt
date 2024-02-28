package com.example.t07ejercicio04

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Recupero el boton
        val botonLlamar: Button = findViewById(R.id.botonLlamar)

        botonLlamar.setOnClickListener {
            val intentLlamar: Intent = Intent(Intent.ACTION_DIAL)
            intentLlamar.setData(Uri.parse("tel:639468634"))
            startActivity(intentLlamar)
        }
    }
}