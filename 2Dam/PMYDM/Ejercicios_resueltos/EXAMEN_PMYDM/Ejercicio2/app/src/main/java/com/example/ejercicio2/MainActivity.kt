package com.example.ejercicio2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    //Componentes
    private lateinit var editTextNombre: EditText
    private lateinit var editTextTelefono: EditText
    private lateinit var botonGuardar: Button
    private lateinit var editTextBuscarNombre: EditText
    private lateinit var botonMostrar: Button

    //ArrayList contactos
    private var contactos: ArrayList<Contacto> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //FUNCIONALIDAD======================

        //Recupero los componentes
        editTextNombre = findViewById(R.id.editTextNombre)
        editTextTelefono = findViewById(R.id.editTextTelefono)
        botonGuardar = findViewById(R.id.buttonGuardar)
        editTextBuscarNombre = findViewById(R.id.editTextBuscarNombre)
        botonMostrar = findViewById(R.id.buttonMostrar)

        //Si el usuario pulsa el botón guardar
        botonGuardar.setOnClickListener {
            guardar()
        }

        //Si el usuario pulsa el botón mostrar
        botonMostrar.setOnClickListener {
            mostrarContacto()
        }
    }

    //Función guardar
    private fun guardar() {
        //Recupero los valores nombre y teléfono controlando que puedan ser nulos
        var nombre: String = editTextNombre.text.toString()
        var telefono: Int? = editTextTelefono.text.toString().toIntOrNull()
        //Controlo si alguno de los dos esté vacío
        if (nombre == "" || telefono == null) {
            Toast.makeText(
                applicationContext,
                getString(R.string.toast_camposVacios),
                Toast.LENGTH_SHORT
            ).show()
        } else {
            //Creo objeto de la clase contacto
            val contacto: Contacto = Contacto(nombre, telefono)

            //Añado el contacto al Arraylist
            contactos.add(contacto)

            //Muestro toast satisfactorio
            Toast.makeText(
                applicationContext,
                getString(R.string.toast_contactoGuardado),
                Toast.LENGTH_SHORT
            ).show()

            //Limpio los campos de los EditText
            editTextNombre.setText("")
            editTextTelefono.setText("")
        }
    }

    //Función mostrar
    private fun mostrarContacto() {
        //Recupero el valor introducido en el campo buscar contacto
        var nombre: String = editTextBuscarNombre.text.toString()

        //Controlo que la agenda no esté vacía
        if (contactos.isEmpty()) {
            Toast.makeText(
                applicationContext,
                getString(R.string.toast_noHayContactos),
                Toast.LENGTH_SHORT
            ).show()
        } else {
            //Controlo si el campo está vacío
            if (nombre == "") {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.toast_buscarNombreVacio),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                //Compruebo que exista el contacto
                var contacto: Contacto? = buscarContacto(nombre)
                if (contacto != null) {
                    //Abro el intent Mostrar pasándole los extras
                    val intentMostrar: Intent =
                        Intent(applicationContext, MostrarActivity::class.java)
                    intentMostrar.putExtra("nombre", contacto.getNombre())
                    intentMostrar.putExtra("telefono", contacto.getTelefono())
                    startActivity(intentMostrar)
                } else {
                    //Toast contacto no encontrado
                    Toast.makeText(
                        applicationContext,
                        getString(R.string.toast_contactoNoEncontrado),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }

    //Método que recorre los contactos, si encuentra el contacto lo devuelve, sino devuelve null
    private fun buscarContacto(nombre: String): Contacto? {
        contactos.forEach { contacto ->
            if (contacto.getNombre().equals(nombre)) {
                return contacto
            }
        }
        //Si no lo encuentra retorna null
        return null
    }
}