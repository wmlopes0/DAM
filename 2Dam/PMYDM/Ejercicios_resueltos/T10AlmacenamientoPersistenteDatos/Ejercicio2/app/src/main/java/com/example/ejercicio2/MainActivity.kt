package com.example.ejercicio2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {

    //Variables globales
    private lateinit var inputFecha: EditText
    private lateinit var inputTareas: EditText
    private lateinit var botonGuardar: Button
    private lateinit var botonRecuperar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Recuperamos los componentes
        inputFecha = findViewById(R.id.inputFecha)
        inputTareas = findViewById(R.id.inputTareas)
        botonGuardar = findViewById(R.id.botonGuardar)
        botonRecuperar = findViewById(R.id.botonRecuperar)

        //Acción guardar
        botonGuardar.setOnClickListener {
            guardarTareas()
        }
        //Acción recuperar
        botonRecuperar.setOnClickListener {
            recuperarTareas()
        }
    }

    private fun guardarTareas() {
        //Recupero la fecha y el texto controlando que no sean nulos
        var fecha: String = inputFecha.text.toString()
        var texto: String = inputTareas.text.toString()
        if (!fecha.isNullOrEmpty()) {
            if (!texto.isNullOrEmpty()) {
                //Escribo el fichero
                escribirFichero(fecha, texto)
            } else {
                Toast.makeText(applicationContext, getText(R.string.toastFaltaTexto), Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            Toast.makeText(applicationContext, getText(R.string.toastFaltaFecha), Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun escribirFichero(nombreFichero: String, texto: String) {
        try {
            val fileOutputStream =
                applicationContext.openFileOutput(nombreFichero + ".txt", Context.MODE_PRIVATE)
            val outputStreamWriter = OutputStreamWriter(fileOutputStream)
            outputStreamWriter.write(texto)

            outputStreamWriter.close()
            fileOutputStream.close()

            Toast.makeText(applicationContext, R.string.toastTareasGuardadas, Toast.LENGTH_SHORT)
                .show()
            //Vacio campos
            inputTareas.setText("")
            inputFecha.setText("")
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun recuperarTareas() {
        //Recupero la fecha controlando que no sea nula
        var fecha: String = inputFecha.text.toString()
        if (!fecha.isNullOrEmpty()) {
            leerFichero(fecha)
        } else {
            Toast.makeText(applicationContext, getText(R.string.toastFaltaFecha), Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun leerFichero(nombreFichero: String) {
        try {
            val fileInputStream = applicationContext.openFileInput(nombreFichero + ".txt")
            val inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)

            val tareasBuilder: StringBuilder = StringBuilder()

            var linea: String? = bufferedReader.readLine()
            while (linea != null) {
                tareasBuilder.append(linea)
                tareasBuilder.append("\n")
                linea = bufferedReader.readLine()
            }

            bufferedReader.close()
            inputStreamReader.close()
            fileInputStream.close()

            inputTareas.setText(tareasBuilder.toString())
        } catch (e: FileNotFoundException) {
            Toast.makeText(applicationContext, getText(R.string.toastNotFound), Toast.LENGTH_SHORT)
                .show()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}