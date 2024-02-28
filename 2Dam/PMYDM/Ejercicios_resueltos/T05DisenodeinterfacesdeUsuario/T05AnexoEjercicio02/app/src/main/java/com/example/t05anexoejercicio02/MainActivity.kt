package com.example.t05anexoejercicio02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Recuperamos los botones
        val botonInicio: Button = findViewById(R.id.botonInicio)
        val botonSumador: Button = findViewById(R.id.botonSumador)

        botonInicio.setOnClickListener {
            var inicioIsClicked: Boolean = true
            loadFragment(inicioIsClicked)
        }

        botonSumador.setOnClickListener {
            var inicioIsClicked: Boolean = false
            loadFragment(inicioIsClicked)
        }
    }

    private fun loadFragment(inicioIsClicked: Boolean) {

        //Clase para interactuar con un fragment dentro de un activity
        val fragmentManager: FragmentManager = supportFragmentManager
        //Clase para realizar operaciones con fragment(trasnsacciones)
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()

        //Reemplaza el contenido del FragmentContainerView con el nuevo Fragment en funcion del botón pulsado
        if (inicioIsClicked) {
            transaction.replace(R.id.fragmentContainerView2, FragmentBienvenida())
        } else {
            transaction.replace(R.id.fragmentContainerView2, FragmentSumador())
        }

        //Commit de la transaccion
        transaction.commit()
    }
}