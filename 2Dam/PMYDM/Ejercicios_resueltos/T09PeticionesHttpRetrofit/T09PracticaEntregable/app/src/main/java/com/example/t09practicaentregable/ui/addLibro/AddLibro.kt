package com.example.t09practicaentregable.ui.addLibro

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.t09practicaentregable.R
import com.example.t09practicaentregable.databinding.FragmentAddLibroBinding
import com.example.t09practicaentregable.model.Libro
import com.example.t09practicaentregable.service.LibroRetrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddLibro : Fragment() {

    //VARIABLES GLOBALES=====================================
    private var _binding: FragmentAddLibroBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    private lateinit var inputId: EditText
    private lateinit var inputAutor: EditText
    private lateinit var inputTitulo: EditText
    private lateinit var inputGenero: EditText
    private lateinit var inputPublicacion: EditText
    private lateinit var inputPrecio: EditText
    private lateinit var inputImagen: EditText
    private lateinit var botonAdd: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddLibroBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //LÓGICA===============================================
        //Recuperamos los componentes
        inputId = root.findViewById(R.id.inputId)
        inputAutor = root.findViewById(R.id.inputAutor)
        inputTitulo = root.findViewById(R.id.inputTitulo)
        inputGenero = root.findViewById(R.id.inputGenero)
        inputPublicacion = root.findViewById(R.id.inputPublicacion)
        inputPrecio = root.findViewById(R.id.inputPrecio)
        inputImagen = root.findViewById(R.id.inputImagen)
        botonAdd = root.findViewById(R.id.botonAdd)

        //Acción botón Add
        botonAdd.setOnClickListener {
            guardarLibro()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //===============================================================
    private fun guardarLibro() {
        //Recuperamos los valores
        var id = inputId.text.toString()
        var autor = inputAutor.text.toString()
        var titulo = inputTitulo.text.toString()
        var genero = inputGenero.text.toString()
        var publicacion = inputPublicacion.text.toString()
        var precio = inputPrecio.text.toString()
        var imagen = inputImagen.text.toString()
        //Compruebo que los valores no sean nulos excepto la imagen que si se puede mantener nula y se asignara una por defecto
        when {
            id.isNullOrEmpty() || titulo.isNullOrEmpty() || genero.isNullOrEmpty() || publicacion.isNullOrEmpty() || precio.isNullOrEmpty() -> {
                Toast.makeText(requireContext(), getText(R.string.toastCamposVaciosImagen), Toast.LENGTH_SHORT)
                    .show()
            }
            else -> {
                if (imagen.isNullOrEmpty()){
                    imagen = "https://images.vexels.com/media/users/3/157233/isolated/preview/f6bf1094d2550ae80df80f6840f7d5e6-icono-de-libro-de-texto-simple.png"
                }
                lanzarPost(id, autor, titulo, genero, publicacion, precio, imagen)
                Toast.makeText(requireContext(), getText(R.string.toastInsertCorrecto), Toast.LENGTH_SHORT)
                    .show()
                limpiarCampos()
            }
        }
    }

    private fun lanzarPost(
        id: String,
        autor: String,
        titulo: String,
        genero: String,
        publicacion: String,
        precio: String,
        imagen: String
    ) {
        Log.d("DebugLog", "lanzarPost iniciada")
        CoroutineScope(Dispatchers.IO).launch {
            try {
                Log.d("DebugLog", "Dentro de la corrutina")
                // Creo un objeto Modulo con los datos del usuario
                var libroNuevo: Libro =
                    Libro(autor, genero, id, imagen, precio, publicacion, titulo)
                //Hago el post
                LibroRetrofit.apiService.insertLibro(libroNuevo)
                Log.d("DebugLog", "Post API realizada")
            } catch (e: Exception) {
                Log.e("ErrorLog", "Excepción en lanzarPeticion: ${e.message}")
            }
        }
    }

    private fun limpiarCampos() {
        inputId.setText("")
        inputTitulo.setText("")
        inputAutor.setText("")
        inputGenero.setText("")
        inputPublicacion.setText("")
        inputPrecio.setText("")
        inputImagen.setText("")
    }
}