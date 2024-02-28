package com.example.t08ejercicio10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog


class MainActivity : AppCompatActivity(), View.OnClickListener {
    //COMPONENTES
    private lateinit var botonAlerta: Button
    private lateinit var botonConfirmacion: Button
    private lateinit var botonSeleccion: Button
    private lateinit var botonSeleccion2: Button
    private lateinit var botonSeleccion3: Button
    private lateinit var botonPersonalizado: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //LÓGICA=================================

        //Recuperamos valores
        botonAlerta = findViewById(R.id.botonDialogoAlerta)
        botonConfirmacion = findViewById(R.id.botonDialogoConfirmacion)
        botonSeleccion = findViewById(R.id.botonDialogoSeleccion)
        botonSeleccion2 = findViewById(R.id.botonDialogoSeleccion2)
        botonSeleccion3 = findViewById(R.id.botonDialogoSeleccion3)
        botonPersonalizado = findViewById(R.id.botonDialogoPersonalizado)

        //Establecemos el OnClickListener
        botonAlerta.setOnClickListener(this)
        botonConfirmacion.setOnClickListener(this)
        botonSeleccion.setOnClickListener(this)
        botonSeleccion2.setOnClickListener(this)
        botonSeleccion3.setOnClickListener(this)
        botonPersonalizado.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.botonDialogoAlerta -> dialogoAlerta()
            R.id.botonDialogoConfirmacion -> dialogoConfirmacion()
            R.id.botonDialogoSeleccion -> dialogoSeleccion()
            R.id.botonDialogoSeleccion2 -> dialogoSeleccion2()
            R.id.botonDialogoSeleccion3 -> dialogoSeleccion3()
            R.id.botonDialogoPersonalizado -> dialogoPersonalizado()
        }
    }

    private fun dialogoAlerta() {
        //Creamos cuadroDialogo
        val cuadroDialogo: AlertDialog.Builder = AlertDialog.Builder(this)
        //Establecemos título y mensaje
        cuadroDialogo.setTitle("Diálogo de alerta")
        cuadroDialogo.setMessage("Esto es un cuadro de diálogo de alerta")
        //Configuramos el botón aceptar
        cuadroDialogo.setPositiveButton("ACEPTAR") { dialog, which ->
            dialog.cancel()
        }
        //Mostramos
        cuadroDialogo.show()
    }

    private fun dialogoConfirmacion() {
        //Creamos cuadroDialogo
        val cuadroDialogo: AlertDialog.Builder = AlertDialog.Builder(this)
        //Establecemos título y mensaje
        cuadroDialogo.setTitle("Diálogo de confirmación")
        cuadroDialogo.setMessage("¿Eres mayor de edad?")

        //Configuramos el positiveButton
        cuadroDialogo.setPositiveButton("SI") { dialog, which ->
            Toast.makeText(applicationContext, "¡Bienvenido!", Toast.LENGTH_SHORT).show()
            dialog.cancel()
        }

        //Configuramos el negativeButton
        cuadroDialogo.setNegativeButton("NO") { dialog, which ->
            Toast.makeText(applicationContext, "ACCESO DENEGADO", Toast.LENGTH_SHORT).show()
            dialog.cancel()
        }

        //Mostramos
        cuadroDialogo.show()
    }

    private fun dialogoSeleccion() {
        //Creamos array de elementos de seleccion
        val plataformas: Array<String> = arrayOf("Netflix", "HBO", "Amazon Prime", "Disney Plus")

        //Creamos cuadroDialogo
        val cuadroDialogo: AlertDialog.Builder = AlertDialog.Builder(this)

        //Establecemos título y mensaje
        cuadroDialogo.setTitle("¿Plataforma Streaming favorita?")

        //Configuramos los items
        cuadroDialogo.setItems(plataformas) { dialog, which ->
            Toast.makeText(
                applicationContext,
                "Has elegido ${plataformas[which]}",
                Toast.LENGTH_SHORT
            ).show()
            dialog.cancel()
        }

        //Mostramos
        cuadroDialogo.show()
    }

    private fun dialogoSeleccion2() {
        //Creamos array de elementos de seleccion
        val plataformas: Array<String> = arrayOf("Netflix", "HBO", "Amazon Prime", "Disney Plus")

        //Creamos cuadroDialogo
        val cuadroDialogo: AlertDialog.Builder = AlertDialog.Builder(this)

        //Establecemos título y mensaje
        cuadroDialogo.setTitle("¿Plataforma Streaming favorita?")

        //Configuramos los items seleccionando la opción por defecto
        cuadroDialogo.setSingleChoiceItems(plataformas, -1) { dialog, which ->
            Toast.makeText(
                applicationContext,
                "Has elegido ${plataformas[which]}",
                Toast.LENGTH_SHORT
            ).show()
            dialog.cancel()
        }

        //Mostramos
        cuadroDialogo.show()
    }

    private fun dialogoSeleccion3() {
        //Creamos array de elementos de seleccion
        val plataformas: Array<String> = arrayOf("Netflix", "HBO", "Amazon Prime", "Disney Plus")

        //Creamos array de elementos seleccionados por defecto
        val plataformasSeleccionadas: BooleanArray = booleanArrayOf(false, true, true, false)

        //Creamos cuadroDialogo
        val cuadroDialogo: AlertDialog.Builder = AlertDialog.Builder(this)

        //Establecemos título y mensaje
        cuadroDialogo.setTitle("¿Plataforma Streaming favorita?")

        //Configuramos los items seleccionando la opción por defecto
        cuadroDialogo.setMultiChoiceItems(
            plataformas,
            plataformasSeleccionadas
        ) { dialog, which, isChecked ->
            if (isChecked) {
                plataformasSeleccionadas[which] = true
                Toast.makeText(
                    applicationContext,
                    "Has seleccionado ${plataformas[which]}",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                plataformasSeleccionadas[which] = false
                Toast.makeText(
                    applicationContext,
                    "Has deseleccionado ${plataformas[which]}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        //Mostramos
        cuadroDialogo.show()
    }

    private fun dialogoPersonalizado() {
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