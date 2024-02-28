package com.example.t06ejercicio02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val boton : Button = findViewById(R.id.button)
        boton.setOnClickListener(){
            Toast.makeText(applicationContext,getString(R.string.toast),Toast.LENGTH_SHORT).show()
        }
    }
}