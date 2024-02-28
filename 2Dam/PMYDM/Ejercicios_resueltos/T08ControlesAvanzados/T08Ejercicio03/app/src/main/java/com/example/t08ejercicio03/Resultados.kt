package com.example.t08ejercicio03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class Resultados : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_resultados)

        //Recupero este intent
        val intentActual: Intent = intent

        //Controlo que existan los extras
        if (!intent.hasExtra(getString(R.string.preguntasCorrectas)) || !intent.hasExtra(getString(R.string.preguntasErroneas)) || !intent.hasExtra(getString(R.string.preguntasSinContestar))) {
            Toast.makeText(applicationContext, R.string.toastExtraNulo, Toast.LENGTH_SHORT).show()
        }

        //Recupero los valores
        val correctas: Int = intentActual.getIntExtra(getString(R.string.preguntasCorrectas), 0)
        val erroneas: Int = intentActual.getIntExtra(getString(R.string.preguntasErroneas), 0)
        val sinContestar: Int = intentActual.getIntExtra(getString(R.string.preguntasSinContestar), 0)

        //Recupero los textView de resultados
        val preguntasCorrectas: TextView = findViewById(R.id.preguntasCorrectas)
        val preguntasErroneas: TextView = findViewById(R.id.preguntasErroneas)
        val preguntasSinContestar: TextView = findViewById(R.id.preguntasSinContestar)
        preguntasCorrectas.setText(correctas.toString())
        preguntasErroneas.setText(erroneas.toString())
        preguntasSinContestar.setText(sinContestar.toString())
        calcularYmostrarNota(correctas, erroneas, sinContestar)

        //Recupero Boton inicio y establezco el evento de volver al inicio
        val botonInicio: Button = findViewById(R.id.botonInicio)
        botonInicio.setOnClickListener {
            val actividadPrincipal: Intent = Intent(this, ActividadPrincipal::class.java)
            startActivity(actividadPrincipal)
        }

    }

    private fun calcularYmostrarNota(
        preguntasCorrectas: Int,
        preguntasErroneas: Int,
        preguntasSinContestar: Int
    ) {
        var calificacion: Float = 0f
        calificacion += (preguntasCorrectas * 2.5f)
        calificacion -= preguntasErroneas

        if (calificacion < 0) calificacion = 0f

        //Recupero el TextView Resultado nota y establezco
        val nota: TextView = findViewById(R.id.nota)
        nota.setText(calificacion.toString())
    }
}