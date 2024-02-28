package com.example.ejercicio2

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MostrarActivity : AppCompatActivity() {

    //Componentes
    private lateinit var textViewNombre: TextView
    private lateinit var textViewTelefono: TextView

    //Variables globales
    private var nombre: String? = null
    private var telefono: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar)

        //FUNCIONALIDAD====================

        //Recupero componentes
        textViewNombre = findViewById(R.id.textViewMostrarNombre)
        textViewTelefono = findViewById(R.id.textViewMostrarTelefono)

        //Inserto la barra de herramientas
        setSupportActionBar(findViewById(R.id.toolbar))

        //Recupero y muestro los extras
        recuperarMostrarDatos()
    }

    //Función que recupera los extras y los muestra
    private fun recuperarMostrarDatos() {
        //Recupero el intent actual
        val intentActual: Intent = intent
        //Recupero los extras en un Bundle para comprobar si existen
        val extras: Bundle? = intent.extras
        if (extras == null) {
            Toast.makeText(
                applicationContext,
                getString(R.string.toast_noExistenExtras),
                Toast.LENGTH_SHORT
            ).show()
        } else {
            //Establezco los datos en los TextView y en las variables globales
            textViewNombre.setText(intentActual.getStringExtra("nombre"))
            nombre = intentActual.getStringExtra("nombre")
            textViewTelefono.setText(intentActual.getIntExtra("telefono", 0).toString())
            telefono = intentActual.getIntExtra("telefono", 0)
        }
    }

    //Función para llamar
    private fun llamar() {
        //Me aseguro que el usuario de permisos explicitamente
        val permiso: String = "android.permission.CALL_PHONE"
        if (ContextCompat.checkSelfPermission(this, permiso) == PackageManager.PERMISSION_GRANTED) {
            //Lanzo el intent con los datos recuperados
            val intent: Intent = Intent(Intent.ACTION_CALL)
            intent.setData(Uri.parse("tel:" + telefono))
            startActivity(intent)
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(permiso), 0)
            //Toast avisando de que no tiene permisos
            Toast.makeText(
                applicationContext,
                getString(R.string.toast_noTienePermiso),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    //Métodos para implementar el ActionBar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_mostrar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.phone -> {
                llamar()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}