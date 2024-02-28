package com.example.t09practicaentregable.ui.consultarLibro

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.t09practicaentregable.R
import com.example.t09practicaentregable.databinding.FragmentConsultarLibroBinding
import com.example.t09practicaentregable.model.Libro
import com.example.t09practicaentregable.service.LibroRetrofit
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class ConsultarLibroFragment : Fragment() {

    //VARIABLES GLOBALES==========================================
    private var _binding: FragmentConsultarLibroBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    //Consultar
    private lateinit var inputId: EditText
    private lateinit var botonConsultar: Button

    //Layout del libro consultado
    private lateinit var layoutLibroConsultado: ConstraintLayout
    private lateinit var imagen: ImageView
    private lateinit var textViewTitulo: TextView
    private lateinit var textViewAutor: TextView
    private lateinit var textViewGenero: TextView
    private lateinit var textViewPublicacion: TextView
    private lateinit var textViewPrecio: TextView
    private lateinit var titulo: TextView
    private lateinit var autor: TextView
    private lateinit var genero: TextView
    private lateinit var publicacion: TextView
    private lateinit var precio: TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConsultarLibroBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //LÓGICA==================================================
        //Recuperamos los componentes
        inputId = root.findViewById(R.id.editTextInput)
        botonConsultar = root.findViewById(R.id.botonConsultar)
        imagen = root.findViewById(R.id.imagenLibro)
        titulo = root.findViewById(R.id.tituloValor)
        autor = root.findViewById(R.id.autorValor)
        genero = root.findViewById(R.id.generoValor)
        publicacion = root.findViewById(R.id.publicacionValor)
        precio = root.findViewById(R.id.precioValor)
        //Recupero textView
        textViewTitulo = root.findViewById(R.id.textViewTitulo)
        textViewAutor = root.findViewById(R.id.textViewAutor)
        textViewGenero = root.findViewById(R.id.textViewGenero)
        textViewPublicacion = root.findViewById(R.id.textViewPublicacion)
        textViewPrecio = root.findViewById(R.id.textViewPrecio)

        // Inicializo los componentes del layoutLibroConsultado como ocultos
        ocultarComponentesLibroConsultado()

        //Acción botón consultar
        botonConsultar.setOnClickListener {
            //Recupero el valor del input
            var id: Int? = inputId.text.toString().toIntOrNull()
            if (id != null) {
                lanzarPeticion(id)//Lanzamos la petición
            } else {
                Toast.makeText(requireContext(), getText(R.string.toastIdNulo), Toast.LENGTH_SHORT)
                    .show()
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //=================================================================================
    private fun lanzarPeticion(id: Int) {
        Log.d("DebugLog", "lanzarPeticion iniciada")
        // Lanza la corrutina en segundo plano
        CoroutineScope(Dispatchers.IO).launch {
            try {
                Log.d("DebugLog", "Dentro de la corrutina")
                // Petición HTTP para obtener el objeto libro
                val libroResponse: Response<Libro> = LibroRetrofit.apiService.getLibroId(id)
                Log.d("DebugLog", "Petición API realizada")
                var libro: Libro?
                if (libroResponse.isSuccessful) {
                    Log.d("DebugLog", "Respuesta exitosa")
                    // Se obtiene el objeto libro del body del objeto Response
                    libro = libroResponse.body()

                    if (libro != null) {
                        mostrarDatos(libro)//Seteo los datos
                    } else {
                        Log.d("DebugLog", "Objeto módulo es nulo")
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

    private fun ocultarComponentesLibroConsultado() {
        imagen.visibility = View.GONE
        textViewTitulo.visibility = View.GONE
        textViewAutor.visibility = View.GONE
        textViewGenero.visibility = View.GONE
        textViewPublicacion.visibility = View.GONE
        textViewPrecio.visibility = View.GONE
    }

    private fun mostrarComponentesLibroConsultado() {
        imagen.visibility = View.VISIBLE
        textViewTitulo.visibility = View.VISIBLE
        textViewAutor.visibility = View.VISIBLE
        textViewGenero.visibility = View.VISIBLE
        textViewPublicacion.visibility = View.VISIBLE
        textViewPrecio.visibility = View.VISIBLE
    }

    private fun mostrarDatos(libro: Libro?) {
        GlobalScope.launch(Dispatchers.Main) {
            // Hago visible los componentes del layout
            mostrarComponentesLibroConsultado()
            //Setteo
            titulo.setText(libro?.titulo)
            autor.setText(libro?.autor)
            genero.setText(libro?.genero)
            publicacion.setText(libro?.publicacion)
            precio.setText(libro?.precio + "€")

            //Muestro imagen con la libreria Piccaso
            Picasso.get()
                .load(libro?.imagen)//Cargamos la url de la imagen del campo imagen de nuestro objeto Libro
                .placeholder(R.drawable.libro) // Imagen de placeholder
                .error(R.drawable.libro) // Imagen de error
                .into(imagen) //Asignamos el ImageView
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