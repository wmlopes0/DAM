package com.example.t08ejercicio07

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {

    lateinit private var layoutPrincipal: ConstraintLayout
    private var colorBlanco = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Recupero el layout principal
        layoutPrincipal = findViewById(R.id.layoutPrincipal)

        setSupportActionBar(findViewById(R.id.toolbar))

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_iconos, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.opcion1 -> {
                Toast.makeText(this, "Soy un gato", Toast.LENGTH_SHORT).show()
                return true
            }

            R.id.opcion2 -> {
                if (colorBlanco){
                    layoutPrincipal.setBackgroundColor(getColor(R.color.verde))
                    colorBlanco = false
                }else{
                    layoutPrincipal.setBackgroundColor(getColor(R.color.white))
                    colorBlanco = true
                }
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }
}