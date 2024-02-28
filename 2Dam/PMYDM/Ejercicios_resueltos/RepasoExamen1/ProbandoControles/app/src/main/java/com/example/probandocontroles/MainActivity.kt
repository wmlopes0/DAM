package com.example.probandocontroles

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var texto: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //=======================================
        texto = findViewById<TextView>(R.id.texto)
        val typeFace: Typeface = Typeface.createFromAsset(assets, "COMICATE.TTF")
        texto.setTypeface(typeFace)

        Toast.makeText(applicationContext, "HOla que tal jiji", Toast.LENGTH_SHORT).show()

        //======================================================
        val grupo: RadioGroup = findViewById(R.id.grupo)
        var seleccionado: Int = grupo.checkedRadioButtonId

        grupo.setOnCheckedChangeListener { group, checkedId ->
            var seleccionado: Int = grupo.checkedRadioButtonId
            when (seleccionado) {
                R.id.opcion1 -> Toast.makeText(applicationContext, "Opcion1", Toast.LENGTH_SHORT)
                    .show()

                R.id.opcion2 -> {
                    Toast.makeText(applicationContext, "Activity 2", Toast.LENGTH_SHORT)
                        .show()
                    val intent: Intent = Intent(this, Activity2::class.java)
//                    intent.putExtra("Nombre","Walter")
//                    intent.putExtra("Edad",25)
                    startActivity(intent)
                }

                R.id.opcion3 -> Toast.makeText(applicationContext, "Opcion3", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }

}