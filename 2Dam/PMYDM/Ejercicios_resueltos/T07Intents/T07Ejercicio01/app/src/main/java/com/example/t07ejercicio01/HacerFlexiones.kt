package com.example.t07ejercicio01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class HacerFlexiones : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hacer_flexiones)
        //Toast para mostrar el estado
        Toast.makeText(applicationContext, R.string.toastOnCreate, Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        //Toast para mostrar el estado
        Toast.makeText(applicationContext, R.string.toastOnStart, Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        //Toast para mostrar el estado
        Toast.makeText(applicationContext, R.string.toastOnResume, Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        //Toast para mostrar el estado
        Toast.makeText(applicationContext, R.string.toastOnPause, Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        //Toast para mostrar el estado
        Toast.makeText(applicationContext, R.string.toastOnStop, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        //Toast para mostrar el estado
        Toast.makeText(applicationContext, R.string.toastOnDestroy, Toast.LENGTH_SHORT).show()
    }
}