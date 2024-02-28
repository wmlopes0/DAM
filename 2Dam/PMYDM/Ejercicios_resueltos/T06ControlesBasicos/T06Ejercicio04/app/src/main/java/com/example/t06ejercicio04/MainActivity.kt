package com.example.t06ejercicio04

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        LÓGICA =====================================

//        Recupero el TextView del título para cambiarle la letra
        val titulo: TextView = findViewById(R.id.titulo)
        val letraPersonalizada: Typeface = Typeface.createFromAsset(assets, "This Night.ttf")
        titulo.setTypeface(letraPersonalizada)

//        Recupero el boton OPERAR
        val operar: Button = findViewById(R.id.botonOperar)

//        Recupero el TextView Resultado
        val resultado: TextView = findViewById(R.id.resultado)

//        Recupero los view EditText
        val editTextNum1: EditText = findViewById(R.id.editTextNumber)
        val editTextNum2: EditText = findViewById(R.id.editTextNumber2)

//        ACCIÓN CLICK BOTÓN
        accionClickBoton(operar, resultado, editTextNum1, editTextNum2)
    }

    //    Función Click Botón
    private fun accionClickBoton(
        operar: Button,
        resultado: TextView,
        editTextNum1: EditText,
        editTextNum2: EditText
    ) {
        operar.setOnClickListener() {
//        Recupero los valores controlando que pueda ser null
            val numero1: Int? = editTextNum1.text.toString().toIntOrNull()
            val numero2: Int? = editTextNum2.text.toString().toIntOrNull()

//        Recupero los view Checkbox
            val sumar: CheckBox = findViewById(R.id.checkBoxSumar)
            val restar: CheckBox = findViewById(R.id.checkBoxRestar)

//            Controlo que haya valores
            if (numero1 == null || numero2 == null) {
                Toast.makeText(applicationContext, R.string.toastNumeroVacio, Toast.LENGTH_SHORT)
                    .show()
            } else {
//                Controlo que alguna opción esté seleccionada
                if (!sumar.isChecked && !restar.isChecked) {
                    Toast.makeText(applicationContext, R.string.toastCheckVacio, Toast.LENGTH_SHORT)
                        .show()
                } else {
//                    Muestro resultado
                    resultado.setText(crearMensajeResultado(numero1, numero2, sumar, restar))
                }
            }
        }
    }

    //    Funcion para crear el mensaje del resultado
    private fun crearMensajeResultado(
        numero1: Int,
        numero2: Int,
        sumar: CheckBox,
        restar: CheckBox
    ): String {
        var resultado: Int = 0
        var mensajeResultado: String = ""
        //Si está seleccionado sumar
        if (sumar.isChecked) {
            resultado = numero1 + numero2
            mensajeResultado += "La suma es: $resultado."
        }
        //Si está seleccionado restar
        if (restar.isChecked) {
            resultado = numero1 - numero2
            mensajeResultado += " La resta es: $resultado."
        }
        return mensajeResultado
    }
}