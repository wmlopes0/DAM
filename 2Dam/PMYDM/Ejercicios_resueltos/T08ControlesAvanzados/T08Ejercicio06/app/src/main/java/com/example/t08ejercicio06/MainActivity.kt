package com.example.t08ejercicio06

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.mi_primer_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.opcion1 -> {
                Toast.makeText(this, "Pulsada opción 1", Toast.LENGTH_SHORT).show()
                return true
            }

            R.id.opcion2 -> {
                Toast.makeText(this, "Pulsada opción 2", Toast.LENGTH_SHORT).show()
                return true
            }

            R.id.opcion3 -> {
                Toast.makeText(this, "Pulsada opción 3", Toast.LENGTH_SHORT).show()
                return true
            }

            R.id.opcion3_1 -> {
                Toast.makeText(this, "Pulsada opción 3.1", Toast.LENGTH_SHORT).show()
                return true
            }

            R.id.opcion3_2 -> {
                Toast.makeText(this, "Pulsada opción 3.2", Toast.LENGTH_SHORT).show()
                return true
            }

            R.id.opcion3_3 -> {
                Toast.makeText(this, "Pulsada opción 3.3", Toast.LENGTH_SHORT).show()
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }
}