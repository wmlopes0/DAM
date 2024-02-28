package com.example.t06ejercicio06

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Lógica
        //Recupero los componentes
        val boton: ImageButton = findViewById(R.id.boton)
        val imagen: ImageView = findViewById(R.id.imagen)

        //Obtengo el identificador de las imagenes para poder comparar
        var estadoLlamada: Int = R.drawable.descolgar

        //Acción boton
        boton.setOnClickListener {
            if (estadoLlamada == R.drawable.descolgar) {
                boton.setImageResource(R.drawable.colgar)
                imagen.setImageResource(R.drawable.ws2)
                estadoLlamada = R.drawable.colgar
            } else {
                boton.setImageResource(R.drawable.descolgar)
                imagen.setImageResource(R.drawable.ws1)
                estadoLlamada = R.drawable.descolgar
            }
        }
    }


}