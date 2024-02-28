package com.example.t09ejercicio1.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.t09ejercicio1.R
import com.example.t09ejercicio1.modelo.Player
import com.example.t09ejercicio1.servicios.PlayerRetrofit.apiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class PrimeraVista : AppCompatActivity() {

    //Variables globales
    private lateinit var inputIdPlayer: EditText
    private lateinit var botonConsultar: Button
    private lateinit var firstNameValue: TextView
    private lateinit var lastNameValue: TextView
    private lateinit var teamValue: TextView
    private lateinit var botonNewActivity: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_primera_vista)
        //LÓGICA==========================================
        //Recupero los componentes necesarios
        inputIdPlayer = findViewById(R.id.inputIdPlayer)
        botonConsultar = findViewById(R.id.botonConsultar)
        firstNameValue = findViewById(R.id.firstNameValue)
        lastNameValue = findViewById(R.id.lastNameValue)
        teamValue = findViewById(R.id.teamValue)
        botonNewActivity = findViewById(R.id.botonNewActivity)
        //Acción botón consultar
        botonConsultar.setOnClickListener {
            consultar()
        }
        //Acción botón NewActivity
        botonNewActivity.setOnClickListener{
            val segundaVista:Intent = Intent(this,SegundaVista::class.java)
            startActivity(segundaVista)
        }
    }

    //Función consultar
    private fun consultar() {
        //Recupero el input del usuario controlando que no esté vacio
        var idPlayer: Int? = inputIdPlayer.text.toString().toIntOrNull()
        if (idPlayer != null) {
            lanzarPeticion(idPlayer)
        } else {
            Toast.makeText(applicationContext, getString(R.string.inputVacio), Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun lanzarPeticion(playerId: Int) {
        Log.d("MainActivity", "Lanzando petición para el jugador con ID: $playerId")
        //Lanza la corrutina en segundo plano
        CoroutineScope(Dispatchers.IO).launch {
            //Petición HTTP para obtener el jugador a través de su id
            val playerResponse: Response<Player> = apiService.getPlayerId(playerId)
            //Tratamiento de los datos recuperados de la petición HTTP
            //El objeto Player que obtenemos de la petición HTTP debe ser nulable (puede ser que la petición
            //no nos devuelva ningún objeto con el id especificado).
            var player: Player? = null
            if (playerResponse.isSuccessful) {
                //Se obtiene el objeto con los datos del jugador del body del objeto Response
                player = playerResponse.body()
                mostrarDatos(player)
            } else if (playerResponse.code() == 404) {
                showPlayerNotFoundError()
            } else if (playerResponse.code() == 500) {
                showInternalServerError()
            } else {
                showGeneralError()
            }
        }

    }

    private fun mostrarDatos(player: Player?) {
        runOnUiThread {
            firstNameValue.setText(player?.first_name)
            lastNameValue.setText(player?.last_name)
            teamValue.setText(player?.team?.abbreviation)
        }
    }

    private fun showPlayerNotFoundError() {
        runOnUiThread {
            Toast.makeText(applicationContext, getString(R.string.notFound), Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun showInternalServerError() {
        runOnUiThread {
            Toast.makeText(
                applicationContext,
                getString(R.string.internalServerError),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun showGeneralError() {
        runOnUiThread {
            Toast.makeText(applicationContext, getString(R.string.errorGeneral), Toast.LENGTH_SHORT)
                .show()
        }
    }

}