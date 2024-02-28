package com.example.t05anexotransacionesfragment

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
        val botonAzul: Button = findViewById(R.id.boton1)
        val botonAmarillo: Button = findViewById(R.id.boton2)

        botonAzul.setOnClickListener {
            var blueIsClicked: Boolean = true
            loadFragment(blueIsClicked)
        }

        botonAmarillo.setOnClickListener {
            var blueIsClicked: Boolean = false
            loadFragment(blueIsClicked)
        }
    }

    private fun loadFragment(blueIsClicked: Boolean) {

        //Clase para interactuar con un fragment dentro de un activity
        val fragmentManager: FragmentManager = supportFragmentManager
        //Clase para realizar operaciones con fragment(trasnsacciones)
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()

        //Reemplaza el contenido del FragmentContainerView con el nuevo Fragment en funcion del botón pulsado
        if (blueIsClicked) {
            transaction.replace(R.id.fragmentContainerView, AzulFragment())
        } else {
            transaction.replace(R.id.fragmentContainerView, AmarilloFragment())
        }

        //Commit de la transaccion
        transaction.commit()
    }
}