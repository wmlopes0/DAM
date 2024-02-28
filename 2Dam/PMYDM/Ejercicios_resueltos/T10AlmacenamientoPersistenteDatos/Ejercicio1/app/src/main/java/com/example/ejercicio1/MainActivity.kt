package com.example.ejercicio1

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    //Variables Globales
    private lateinit var inputNombre: EditText
    private lateinit var inputTelefono: EditText
    private lateinit var botonGuardar: Button
    private lateinit var botonBuscar: Button
//    private lateinit var contactos: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Lógica=====================================
        //Recuperamos los componentes
        inputNombre = findViewById(R.id.inputNombre)
        inputTelefono = findViewById(R.id.inputTelefono)
        botonGuardar = findViewById(R.id.botonGuardar)
        botonBuscar = findViewById(R.id.botonBuscar)
//        contactos = getSharedPreferences("contactos", Context.MODE_PRIVATE)

        //Acciones
        botonGuardar.setOnClickListener {
            //Recuperamos valores comprobando que no sean nulos
            var nombre: String? = inputNombre.text.toString()
            var telefono: Int? = inputTelefono.text.toString().toIntOrNull()
            if (!nombre.isNullOrEmpty() && telefono != null) {
                //Instanciamos un objeto de la clase SharedPreferences
                val contactos: SharedPreferences =
                    getSharedPreferences("contactos", Context.MODE_PRIVATE)
                //Creamos un editor
                val editor: SharedPreferences.Editor = contactos.edit()
                //Introducimos el nombre y el telefono
                editor.putInt(nombre, telefono)
                //Hacemos commit
                editor.commit()
                //Vaciamos campos
                inputNombre.setText("")
                inputTelefono.setText("")
                //Check
                Toast.makeText(applicationContext, getText(R.string.toastAddContacto), Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(applicationContext, getText(R.string.toastDatosNulosGuardar), Toast.LENGTH_SHORT)
                    .show()
            }
        }
        botonBuscar.setOnClickListener {
            //Recuperamos valores comprobando que no sean nulos
            var nombre: String? = inputNombre.text.toString()
            if (!nombre.isNullOrEmpty()) {
                    //Instanciamos un objeto de la clase SharedPreferences
                    val contactos: SharedPreferences =
                        getSharedPreferences("contactos", Context.MODE_PRIVATE)
                    //Recuperamos el numero de telefono
                    val telefono: Int = contactos.getInt(nombre, 0)
                    //Seteamos
                    inputTelefono.setText(telefono.toString())
            } else {
                Toast.makeText(applicationContext, getText(R.string.toastSoloNombre), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}