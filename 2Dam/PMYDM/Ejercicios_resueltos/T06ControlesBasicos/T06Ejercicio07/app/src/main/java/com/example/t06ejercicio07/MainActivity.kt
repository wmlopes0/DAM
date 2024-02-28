package com.example.t06ejercicio07

import android.annotation.SuppressLint
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import java.math.BigDecimal
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {
    //Recupero los componentes
    private lateinit var input: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var resultadoEuros: TextView
    private lateinit var resultadoDolares: TextView
    private lateinit var resultadoLibras: TextView
    private lateinit var botonConvertir: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        =====================================================================================
        //Establezco un nuevo tipo de letra para el título
        val titulo: TextView = findViewById(R.id.titulo)
        val typeFace: Typeface = Typeface.createFromAsset(assets, "This Night.ttf")
        titulo.setTypeface(typeFace)
        //Recupero los componentes
        input = findViewById(R.id.editTextNumber)
        radioGroup = findViewById(R.id.radioGroup)
        botonConvertir = findViewById(R.id.button)
        resultadoEuros = findViewById(R.id.resultadoEuros)
        resultadoDolares = findViewById(R.id.resultadoDolares)
        resultadoLibras = findViewById(R.id.resultadoLibras)

        //Evento botón
        botonConvertir.setOnClickListener {
            //Recupero el input controlando que puedan ser nulos
            var cantidad: Int? = input.text.toString().toIntOrNull()
            //Recupero la opcion seleccionada
            var monedaSeleccionada: Int = radioGroup.checkedRadioButtonId

            if (cantidad == null) {
                Toast.makeText(applicationContext, R.string.toastNumeroVacio, Toast.LENGTH_SHORT)
                    .show()
            } else {
                //Controlo que haya alguna opción seleccionada
                if (monedaSeleccionada == -1) {
                    Toast.makeText(applicationContext, R.string.toastCheckVacio, Toast.LENGTH_SHORT)
                        .show()
                } else {
                    //Si está todo_ bien llamo a la funcion calcular
                    calcularResultado(cantidad, monedaSeleccionada)
                }
            }

        }
    }

    //Esta funcion calcula y actualiza los resultados en la pantalla
    @SuppressLint("SetTextI18n")
    private fun calcularResultado(cantidad: Int, monedaSeleccionada: Int) {
        // Define las tasas de cambio.
        val tasaEuroADolar = 1.07
        val tasaEuroALibra = 0.87
        val tasaDolarAEuro = 1 / tasaEuroADolar
        val tasaDolarALibra = 0.81
        val tasaLibraAEuro = 1 / tasaEuroALibra
        val tasaLibraADolar = 1 / tasaDolarALibra

        when (monedaSeleccionada) {
            R.id.radioButtonEuros -> {
                resultadoEuros.setText("$cantidad €")
                resultadoDolares.setText("${BigDecimal(cantidad * tasaEuroADolar).setScale(2, RoundingMode.HALF_EVEN)} $")
                resultadoLibras.setText("${BigDecimal(cantidad * tasaEuroALibra).setScale(2, RoundingMode.HALF_EVEN)} Gbp")
            }

            R.id.radioButtonDolares -> {
                resultadoEuros.setText("${BigDecimal(cantidad * tasaDolarAEuro).setScale(2, RoundingMode.HALF_EVEN)} €")
                resultadoDolares.setText("$cantidad $")
                resultadoLibras.setText("${BigDecimal(cantidad * tasaDolarALibra).setScale(2, RoundingMode.HALF_EVEN)} Gbp")
            }

            R.id.radioButtonLibras -> {
                resultadoEuros.setText("${BigDecimal(cantidad * tasaLibraAEuro).setScale(2, RoundingMode.HALF_EVEN)} €")
                resultadoDolares.setText("${BigDecimal(cantidad * tasaLibraADolar).setScale(2, RoundingMode.HALF_EVEN)} $")
                resultadoLibras.setText("$cantidad Gbp")
            }
        }

    }
}