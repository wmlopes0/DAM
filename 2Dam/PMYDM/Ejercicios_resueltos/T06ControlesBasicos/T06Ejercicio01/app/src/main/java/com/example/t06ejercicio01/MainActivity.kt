package com.example.t06ejercicio01

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textViewEjemplo:TextView = findViewById(R.id.TextViewEjemplo)
        val typeFace : Typeface = Typeface.createFromAsset(assets,"waltographUI.ttf")
        textViewEjemplo.setTypeface(typeFace)
    }
}