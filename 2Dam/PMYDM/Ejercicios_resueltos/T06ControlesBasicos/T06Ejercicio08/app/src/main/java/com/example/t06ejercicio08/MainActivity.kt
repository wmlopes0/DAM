package com.example.t06ejercicio08

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.RadioGroup
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var radioGroup: RadioGroup
    private lateinit var checkBox: CheckBox
    private lateinit var resultado: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Recupero componentes
        radioGroup = findViewById(R.id.radioGroup)
        checkBox = findViewById(R.id.checkBox)
        resultado = findViewById(R.id.resultado)


        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            var itemSelected: Int = radioGroup.checkedRadioButtonId
            when (itemSelected) {
                -1 -> {
                    resultado.setText(getString(R.string.ningunaSeleccionada))
                }

                R.id.realMadrid -> {
                    resultado.setText(getString(R.string.madridSeleccionado))
                }

                R.id.barcelona -> {
                    resultado.setText(getString(R.string.barcelonaSeleccionado))
                }

                R.id.atletico -> {
                    resultado.setText(getString(R.string.atleticoSeleccionado))
                }
            }

        }

        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (checkBox.isChecked) {
                resultado.setText(getString(R.string.meGustaSeleccionado))
            }
        }


    }
}