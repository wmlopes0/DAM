package com.example.t09practicaentregable.ui.listarLibros

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.t09practicaentregable.R
import com.example.t09practicaentregable.databinding.FragmentListarLibrosBinding
import com.example.t09practicaentregable.model.Libro
import com.example.t09practicaentregable.service.LibroRetrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class ListarLibrosFragment : Fragment() {

    //VARIABLES GLOBALES
    private var _binding: FragmentListarLibrosBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListarLibrosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //LÓGICA=================================================================
        //Recupero el recycledView
        recyclerView = root.findViewById(R.id.recyclerView)
        //Vinculo el adaptador con el RecyclerView y uso un LinearLayoutManager para mostrar los ítems en forma de lista vertical.
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        //Lanzo petición
        lanzarPeticion()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //==========================================================================
    private fun lanzarPeticion() {
        Log.d("DebugLog", "lanzarPeticion iniciada")
        // Lanza la corrutina en segundo plano
        CoroutineScope(Dispatchers.IO).launch {
            try {
                Log.d("DebugLog", "Dentro de la corrutina")
                // Petición HTTP para obtener la lista de todos los libros
                val libroResponse: Response<List<Libro>> =
                    LibroRetrofit.apiService.getAllLibros()
                Log.d("DebugLog", "Petición API realizada")
                var libros: List<Libro>?
                if (libroResponse.isSuccessful) {
                    Log.d("DebugLog", "Respuesta exitosa")
                    // Se obtiene la lista de objetos modulo del body del objeto Response
                    libros = libroResponse.body()

                    if (libros != null) {
                        Log.d("DebugLog", "Lista de jugadores no es nula, tamaño: ${libros.size}")
                        mostrarLibrosRecyclerView(libros)
                    } else {
                        Log.d("DebugLog", "Lista de jugadores es nula")
                    }
                } else {
                    Log.e("ErrorLog", "Respuesta fallida, código de error: ${libroResponse.code()}")
                    when (libroResponse.code()) {
                        404 -> showBookNotFoundError()
                        500 -> showInternalServerError()
                        else -> showGeneralError()
                    }
                }
            } catch (e: Exception) {
                Log.e("ErrorLog", "Excepción en lanzarPeticion: ${e.message}")
            }
        }
    }

    private fun mostrarLibrosRecyclerView(listaLibros: List<Libro>) {
        GlobalScope.launch(Dispatchers.Main) {
            //Creo adaptador para el RecyclerView
            val adapter = LibroAdapter(listaLibros)
            //Le asigno el adapter
            recyclerView.adapter = adapter
        }
    }

    private fun showBookNotFoundError() {
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