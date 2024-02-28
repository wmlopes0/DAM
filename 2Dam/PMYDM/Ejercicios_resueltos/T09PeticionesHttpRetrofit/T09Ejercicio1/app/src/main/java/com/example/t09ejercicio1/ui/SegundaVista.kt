package com.example.t09ejercicio1.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.example.t09ejercicio1.R
import com.example.t09ejercicio1.modelo.Data
import com.example.t09ejercicio1.modelo.Player
import com.example.t09ejercicio1.servicios.PlayerRetrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class SegundaVista : AppCompatActivity() {

    //Variables globales
    private lateinit var botonConsultar: Button
    private lateinit var botonAtras: Button
    private lateinit var listViewJugadores: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_segunda_vista)
        //LÓGICA==========================================
        //Recupero los componentes necesarios
        botonConsultar = findViewById(R.id.botonConsultar2)
        botonAtras = findViewById(R.id.botonAtras)
        listViewJugadores = findViewById(R.id.listViewJugadores)

        //Acción botón consultar
        botonConsultar.setOnClickListener {
            lanzarPeticion()
        }

        //Acción botón atrás
        botonAtras.setOnClickListener {
            finish()
        }

    }

    private fun lanzarPeticion() {
        Log.d("DebugLog", "lanzarPeticion iniciada")
        // Lanza la corrutina en segundo plano
        CoroutineScope(Dispatchers.IO).launch {
            try {
                Log.d("DebugLog", "Dentro de la corrutina")
                // Petición HTTP para obtener el jugador a través de su id
                val playerResponse: Response<Data> = PlayerRetrofit.apiService.getAllPlayers()
                Log.d("DebugLog", "Petición API realizada")
                var players: List<Player>?
                if (playerResponse.isSuccessful) {
                    Log.d("DebugLog", "Respuesta exitosa")
                    // Se obtiene la lista de objetos jugador del body del objeto Response
                    players = playerResponse.body()?.`data`

                    if (players != null) {
                        Log.d("DebugLog", "Lista de jugadores no es nula, tamaño: ${players.size}")
                        mostrarListaEnLogcat(players)
                        mostrarJugadoresListView(players)
                    } else {
                        Log.d("DebugLog", "Lista de jugadores es nula")
                    }
                } else {
                    Log.e("ErrorLog", "Respuesta fallida, código de error: ${playerResponse.code()}")
                    when (playerResponse.code()) {
                        404 -> showPlayerNotFoundError()
                        500 -> showInternalServerError()
                        else -> showGeneralError()
                    }
                }
            } catch (e: Exception) {
                Log.e("ErrorLog", "Excepción en lanzarPeticion: ${e.message}")
            }
        }
    }

    fun mostrarListaEnLogcat(lista: List<Player>) {
        runOnUiThread {
            lista.forEach { player ->
                Log.d("ListaLog", player.first_name)
            }
        }
    }

    private fun mostrarJugadoresListView(players: List<Player>) {
        runOnUiThread {
            //Creo adaptador y lo asigno al listView
            listViewJugadores.adapter = crearAdaptador(players)
        }
    }

    private fun crearAdaptador(players: List<Player>): BaseAdapter {
        //ADAPTADOR
        val baseAdapter = object : BaseAdapter() {
            override fun getCount(): Int {
                return players.size
            }

            override fun getItem(position: Int): Any {
                return players.get(position)
            }

            override fun getItemId(position: Int): Long {
                return position.toLong()
            }

            override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
                //INFLAR LA VISTA (si convertView no es null, utilizamos convertView)
                val view: View
                if (convertView == null) {
                    view = layoutInflater.inflate(R.layout.lista_personalizada, parent, false)
                } else {
                    view = convertView
                }

                //CAPTURAR LOS ELEMENTOS LIST ITEM
                val textViewNombre: TextView = view.findViewById<TextView>(R.id.textViewNombre)
                val textViewApellido: TextView = view.findViewById<TextView>(R.id.textViewApellido)
                val textViewEquipo: TextView = view.findViewById<TextView>(R.id.textViewEquipo)

                //ACTUALIZAR LOS ELEMENTOS LIST ITEM
                val player: Player = players.get(position)
                textViewNombre.setText(player.first_name)
                textViewApellido.setText(player.last_name)
                textViewEquipo.setText(player.team.abbreviation)
                return view
            }
        }
        return baseAdapter
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