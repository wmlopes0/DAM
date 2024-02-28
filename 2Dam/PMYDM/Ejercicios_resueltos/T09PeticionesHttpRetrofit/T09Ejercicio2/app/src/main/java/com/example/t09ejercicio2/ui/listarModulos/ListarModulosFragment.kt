package com.example.t09ejercicio2.ui.listarModulos

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.t09ejercicio2.R
import com.example.t09ejercicio2.databinding.FragmentListarModulosBinding
import com.example.t09ejercicio2.model.Modulo
import com.example.t09ejercicio2.service.ModuloRetrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class ListarModulosFragment : Fragment() {

    //VARIABLES GLOBALES
    private var _binding: FragmentListarModulosBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    private lateinit var listViewModulos: ListView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentListarModulosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //LÓGICA==========================
        listViewModulos = root.findViewById(R.id.listViewModulos) //Recuperamos el ListView
        lanzarPeticion()//Lanzamos la petición
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //=================================================================================
    private fun lanzarPeticion() {
        Log.d("DebugLog", "lanzarPeticion iniciada")
        // Lanza la corrutina en segundo plano
        CoroutineScope(Dispatchers.IO).launch {
            try {
                Log.d("DebugLog", "Dentro de la corrutina")
                // Petición HTTP para obtener la lista de todos los módulos
                val moduleResponse: Response<List<Modulo>> =
                    ModuloRetrofit.apiService.getAllModules()
                Log.d("DebugLog", "Petición API realizada")
                var modulos: List<Modulo>?
                if (moduleResponse.isSuccessful) {
                    Log.d("DebugLog", "Respuesta exitosa")
                    // Se obtiene la lista de objetos modulo del body del objeto Response
                    modulos = moduleResponse.body()

                    if (modulos != null) {
                        Log.d("DebugLog", "Lista de jugadores no es nula, tamaño: ${modulos.size}")
                        mostrarModulosListView(modulos)
                    } else {
                        Log.d("DebugLog", "Lista de jugadores es nula")
                    }
                } else {
                    Log.e("ErrorLog", "Respuesta fallida, código de error: ${moduleResponse.code()}")
                    when (moduleResponse.code()) {
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

    private fun mostrarModulosListView(modulos: List<Modulo>) {
        GlobalScope.launch(Dispatchers.Main) {
            //Creo adaptador y lo asigno al listView
            listViewModulos.adapter = crearAdaptador(modulos)
        }
    }

    private fun crearAdaptador(modulos: List<Modulo>): BaseAdapter {
        //ADAPTADOR
        val baseAdapter = object : BaseAdapter() {
            override fun getCount(): Int {
                return modulos.size
            }

            override fun getItem(position: Int): Any {
                return modulos.get(position)
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

                //ACTUALIZAR LOS ELEMENTOS LIST ITEM
                val modulo: Modulo = modulos.get(position)
                textViewNombre.setText(modulo.nombre)
                return view
            }
        }
        return baseAdapter
    }


    private fun showPlayerNotFoundError() {
        GlobalScope.launch(Dispatchers.Main) {
            Toast.makeText(requireContext(), getString(R.string.notFound), Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun showInternalServerError() {
        GlobalScope.launch(Dispatchers.Main) {
            Toast.makeText(
                requireContext(),
                getString(R.string.internalServerError),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun showGeneralError() {
        GlobalScope.launch(Dispatchers.Main) {
            Toast.makeText(requireContext(), getString(R.string.errorGeneral), Toast.LENGTH_SHORT)
                .show()
        }
    }
}