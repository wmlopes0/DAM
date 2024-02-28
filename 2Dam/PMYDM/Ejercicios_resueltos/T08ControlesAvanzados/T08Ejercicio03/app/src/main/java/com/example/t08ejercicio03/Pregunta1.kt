package com.example.t08ejercicio03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner

class Pregunta1 : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    //Variable global
    var correctas:Int = 0
    var erroneas:Int = 0
    var sinContestar:Int = 0
    var respuestaUsuario: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_pregunta1)

        //Recupero el spinner
        val respuesta: Spinner = findViewById(R.id.spinnerRespuesta)
        respuesta.onItemSelectedListener = this
        //Recupero el boton siguiente
        val botonSiguiente: Button = findViewById(R.id.BotonSiguiente)

        //Evento boton
        botonSiguiente.setOnClickListener {
            //Calculo la calificacion
            when (respuestaUsuario) {
                0 -> sinContestar++
                1 -> erroneas++
                2 -> erroneas++
                3 -> correctas++
                4 -> erroneas++
            }

            //Creo intent siguiente y paso calificación
            val pregunta2: Intent = Intent(this, Pregunta2::class.java)
            pregunta2.putExtra(getString(R.string.preguntasCorrectas), correctas)
            pregunta2.putExtra(getString(R.string.preguntasErroneas), erroneas)
            pregunta2.putExtra(getString(R.string.preguntasSinContestar), sinContestar)
            startActivity(pregunta2)
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        respuestaUsuario = position
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        //Se invoca cuando no se selecciona nada
    }
}