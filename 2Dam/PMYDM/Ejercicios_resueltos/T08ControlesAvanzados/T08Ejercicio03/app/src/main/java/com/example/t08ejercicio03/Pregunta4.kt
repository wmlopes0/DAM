package com.example.t08ejercicio03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast

class Pregunta4 : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    //Variable global
    var correctas: Int = 0
    var erroneas: Int = 0
    var sinContestar: Int = 0
    var respuestaUsuario: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_pregunta4)

        //Recupero el spinner
        val respuesta: Spinner = findViewById(R.id.spinnerRespuesta)
        respuesta.onItemSelectedListener = this
        //Recupero el boton siguiente
        val botonSiguiente: Button = findViewById(R.id.BotonSiguiente)

        //Recupero este intent
        val intentActual: Intent = intent

        //Controlo que existan los extras
        if (!intent.hasExtra(getString(R.string.preguntasCorrectas)) || !intent.hasExtra(getString(R.string.preguntasErroneas)) || !intent.hasExtra(getString(R.string.preguntasSinContestar))) {
            Toast.makeText(applicationContext, R.string.toastExtraNulo, Toast.LENGTH_SHORT).show()
        }

        //Recupero los valores
        correctas = intentActual.getIntExtra(getString(R.string.preguntasCorrectas), 0)
        erroneas = intentActual.getIntExtra(getString(R.string.preguntasErroneas), 0)
        sinContestar = intentActual.getIntExtra(getString(R.string.preguntasSinContestar), 0)


        //Evento boton
        botonSiguiente.setOnClickListener {
            //Calculo la calificacion
            when (respuestaUsuario) {
                0 -> sinContestar++
                1 -> correctas++
                2 -> erroneas++
                3 -> erroneas++
                4 -> erroneas++
            }

            //Creo intent siguiente y paso calificación
            val resultados: Intent = Intent(this, Resultados::class.java)
            resultados.putExtra(getString(R.string.preguntasCorrectas), correctas)
            resultados.putExtra(getString(R.string.preguntasErroneas), erroneas)
            resultados.putExtra(getString(R.string.preguntasSinContestar), sinContestar)
            startActivity(resultados)
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        respuestaUsuario = position
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        //Se invoca cuando no se selecciona nada
    }
}