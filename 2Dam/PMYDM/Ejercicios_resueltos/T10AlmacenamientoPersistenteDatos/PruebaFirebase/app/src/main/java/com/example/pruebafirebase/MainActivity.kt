package com.example.pruebafirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    //Variables globales
    private lateinit var inputMarca: EditText
    private lateinit var inputModelo: EditText
    private lateinit var inputPuertas: EditText
    private lateinit var inputVelocidad: EditText
    private lateinit var botonInsertar: Button
    private lateinit var textViewMostrar: TextView

    //Instancia firebase
    private lateinit var firebaseDatabase: FirebaseDatabase

    //Referencia a la base de datos
    private lateinit var databaseReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //LÓGICA=======================================
        //Recuperar los componentes
        inputMarca = findViewById(R.id.inputMarca)
        inputModelo = findViewById(R.id.inputModelo)
        inputPuertas = findViewById(R.id.inputPuertas)
        inputVelocidad = findViewById(R.id.inputVelocidad)
        botonInsertar = findViewById(R.id.botonInsertar)
        textViewMostrar = findViewById(R.id.datosTabla)
        //Firebase
        firebaseDatabase = FirebaseDatabase.getInstance()
        //Establecemos la referencia a la base de datos
        databaseReference =firebaseDatabase.getReference(ReferenciasFirebase.REFERENCIA_BASEDATOS)
        //Asignamos el valueEventListener a la 'tabla' de coches de la bd
        databaseReference.child(ReferenciasFirebase.REFERENCIA_COCHES).addValueEventListener(obtenerValueEventListener())
        //Insertamos datos de prueba
//        insertarDatosPrueba()
        //Acción botón insertar
        botonInsertar.setOnClickListener {
            insertarCoche()
        }

    }

    private fun insertarDatosPrueba() {
        //Creamos dos objetos "Coche"
        val coche1: Coche =
            Coche("Renault", "Megane", 5, 180)
        val coche2: Coche =
            Coche("Ford", "Focus", 5, 200)

        //Insertamos ambos objetos en la bbdd, obteniendo la referencia a la "tabla" coches
        databaseReference.child(ReferenciasFirebase.REFERENCIA_COCHES).push().setValue(coche1)
        databaseReference.child(ReferenciasFirebase.REFERENCIA_COCHES).push().setValue(coche2)
    }

    private fun obtenerValueEventListener(): ValueEventListener {
        //Listener que escucha los cambios que se producen en la bbdd
        val valueEventListener = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                //Variable donde almacenamos el texto con los datos de todos los coches
                var datosCoches: String = ""

                //Se recorre el listado de coches obteniendo todos sus datos
                for (cocheSnapshot in dataSnapshot.children) {
                    val cocheAux: Coche? = cocheSnapshot.getValue(Coche::class.java)
                    if (cocheAux != null) {
                        datosCoches = datosCoches + cocheAux.marca + " - " + cocheAux.modelo +
                                " - Puertas: " + cocheAux.numeroPuertas + " - V.Max: " + cocheAux.velocidadMaxima + "\n"
                    }
                }
                //Se actualiza el textView con los datos de todos los coches
                textViewMostrar.setText(datosCoches)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                //Error de acceso a la base de datos
                Toast.makeText(applicationContext, "Error de acceso", Toast.LENGTH_SHORT).show()
            }
        }

        //Retorno el ValueEventListener
        return valueEventListener
    }

    private fun insertarCoche() {
        //Recupero los valores controlando que no sean nulos
        var marca: String? = inputMarca.text.toString()
        var modelo: String? = inputModelo.text.toString()
        var puertas: Int? = inputPuertas.text.toString().toIntOrNull()
        var velocidad: Int? = inputVelocidad.text.toString().toIntOrNull()
        if (marca.isNullOrEmpty() || modelo.isNullOrEmpty() || puertas == null || velocidad == null) {
            Toast.makeText(applicationContext, "¡Debes introducir todos los datos!", Toast.LENGTH_SHORT)
                .show()
        } else {
            //Creamos objeto "Coche"
            val coche: Coche =
                Coche(marca, modelo, puertas, velocidad)
            //Insertamos objeto en la bbdd, obteniendo la referencia a la "tabla" coches
            databaseReference.child(ReferenciasFirebase.REFERENCIA_COCHES).push().setValue(coche)
            //Limpio campos
            inputModelo.setText("")
            inputMarca.setText("")
            inputPuertas.setText("")
            inputVelocidad.setText("")
            //Toast Check
            Toast.makeText(applicationContext, "¡Coche insertado correctamente!", Toast.LENGTH_SHORT)
                .show()
        }
    }
}