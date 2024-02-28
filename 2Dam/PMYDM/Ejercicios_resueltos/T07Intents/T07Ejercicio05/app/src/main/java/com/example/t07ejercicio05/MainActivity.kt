package com.example.t07ejercicio05

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Recupero boton
        val boton: Button = findViewById(R.id.boton)

        boton.setOnClickListener {
            llamar()
        }

    }

    private fun llamar() {
        val permiso: String = "android.permission.CALL_PHONE"
        if (ContextCompat.checkSelfPermission( this, permiso) == PackageManager.PERMISSION_GRANTED) {
            val intentLlamar: Intent = Intent(Intent.ACTION_CALL)
            intentLlamar.setData(Uri.parse("tel:639468634"))
            startActivity(intentLlamar)

        } else {
            Toast.makeText(getApplicationContext(), R.string.no_permisos_msg, Toast.LENGTH_SHORT).show()
            ActivityCompat.requestPermissions(this, arrayOf(permiso), 0)
        }
    }

}