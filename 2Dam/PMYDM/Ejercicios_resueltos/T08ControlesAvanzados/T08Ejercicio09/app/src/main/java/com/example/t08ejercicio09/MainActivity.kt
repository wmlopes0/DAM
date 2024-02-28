package com.example.t08ejercicio09

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //LÓGICA======================
        val boton: Button = findViewById(R.id.button)

        boton.setOnClickListener {
            cuadroDialogoPersonalizado()
        }
    }

    fun cuadroDialogoPersonalizado() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)

        builder.setTitle("Diálogo Personalizado")

        val inflater: LayoutInflater = layoutInflater
        builder.setView(inflater.inflate(R.layout.cuadro_dialogo_personalizado, null))

        builder.setPositiveButton("ACEPTAR") { dialog, which ->
            dialog.cancel()
        }
        builder.show()
    }
}