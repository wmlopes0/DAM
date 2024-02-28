package com.example.t06ejercicio05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Switch
import android.widget.ToggleButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //LÓGICA
        //Recupero el ToogleButton de las gafas
        val gafas: ToggleButton = findViewById(R.id.toggleButtonGafas)
        //Recupero el Switch del sombrero
        val sombrero: Switch = findViewById(R.id.switchSombrero)
        //Recupero el imageView
        val imagen: ImageView = findViewById(R.id.imageView)

        //Implemento el método setOnCheckChangeListener para cada uno de los botones y dentro llamo a la funcion actualizarImagen
        gafas.setOnCheckedChangeListener { buttonView, isChecked -> actualizarImagen(gafas,sombrero,imagen) }
        sombrero.setOnCheckedChangeListener { buttonView, isChecked -> actualizarImagen(gafas,sombrero,imagen) }

    }

    //Función que actualiza la imagen según las opciones seleccionadas
    private fun actualizarImagen(gafas: ToggleButton, sombrero: Switch, imagen: ImageView) {
        //Controlo las distintas opciones
        when {
            gafas.isChecked && sombrero.isChecked -> imagen.setImageResource(R.drawable.potatogs)
            !gafas.isChecked && sombrero.isChecked -> imagen.setImageResource(R.drawable.potatos)
            gafas.isChecked && !sombrero.isChecked -> imagen.setImageResource(R.drawable.potatog)
            !gafas.isChecked && !sombrero.isChecked -> imagen.setImageResource(R.drawable.potato)
        }
    }
}