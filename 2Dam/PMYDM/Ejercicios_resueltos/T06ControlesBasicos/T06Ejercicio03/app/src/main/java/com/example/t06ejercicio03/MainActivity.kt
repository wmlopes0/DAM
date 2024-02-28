package com.example.t06ejercicio03

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Establezco un nuevo tipo de letra para el título
        val titulo: TextView = findViewById(R.id.titulo)
        val typeFace: Typeface = Typeface.createFromAsset(assets, "This Night.ttf")
        titulo.setTypeface(typeFace)

        //Recupero el radioGroup
        val radioGroup: RadioGroup = findViewById(R.id.miPrimerRadioGroup)

        //Recupero el boton y controlo con un when el toast que tiene que aparecer según la selección del usuario
        val boton : Button = findViewById(R.id.boton)
        boton.setOnClickListener(){
            val idSeleccionado = radioGroup.checkedRadioButtonId //Recupero el radio button seleccionado
            when (idSeleccionado) {
                R.id.masculino -> Toast.makeText(
                    applicationContext,
                    R.string.toast_masculino,
                    Toast.LENGTH_SHORT
                ).show()

                R.id.femenino -> Toast.makeText(
                    applicationContext,
                    R.string.toast_femenino,
                    Toast.LENGTH_SHORT
                ).show()

                -1 -> Toast.makeText(
                    applicationContext,
                    R.string.toast_sinSeleccion,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}