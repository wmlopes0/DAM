package com.example.t09ejercicio2.ui.addModulo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.t09ejercicio2.R
import com.example.t09ejercicio2.databinding.FragmentAddmoduloBinding
import com.example.t09ejercicio2.model.Modulo
import com.example.t09ejercicio2.service.ModuloRetrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Response

class addModuloFragment : Fragment() {

    //VARIABLES GLOBALES
    private var _binding: FragmentAddmoduloBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    private lateinit var inputId: EditText
    private lateinit var inputNombre: EditText
    private lateinit var inputProfesor: EditText
    private lateinit var inputNota: EditText
    private lateinit var botonGuardar: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddmoduloBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //LÓGICA==========================
        //Recuperamos los componentes
        inputId = root.findViewById(R.id.inputId)
        inputNombre = root.findViewById(R.id.inputNombre)
        inputProfesor = root.findViewById(R.id.inputProfesor)
        inputNota = root.findViewById(R.id.inputNota)
        botonGuardar = root.findViewById(R.id.buttonGuardar)

        //Acción botón guardar
        botonGuardar.setOnClickListener {
            guardarModulo()
        }

        return root
    }

    //Función que recupera los valores, comprueba si están vacíos y lanza la peticion en caso contrario
    private fun guardarModulo() {
        //Recuperamos valores input
        var id: Int? = inputId.text.toString().toIntOrNull()
        var nota: Int? = inputNota.text.toString().toIntOrNull()
        var nombre: String? = inputNombre.text.toString()
        var profesor: String? = inputProfesor.text.toString()
        //Compruebo que los valores no sean nulos
        when {
            id == null || nota == null || nombre.isNullOrEmpty() || profesor.isNullOrEmpty() -> {
                Toast.makeText(requireContext(), getText(R.string.toastCamposVacios), Toast.LENGTH_SHORT)
                    .show()
            }

            else -> {
//                val idExiste = existeID(id)
                //Compruebo que el id no exista antes de lanzar la petición
//                if (!idExiste) {
                lanzarPost(id, nombre, profesor, nota)
                Toast.makeText(requireContext(), getText(R.string.toastInsertCorrecto), Toast.LENGTH_SHORT)
                    .show()
                limpiarCampos()
//                } else {
//                    Toast.makeText(requireContext(), getText(R.string.toastIdExistente), Toast.LENGTH_SHORT)
//                        .show()
//                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //=================================================================================
    private fun existeID(id: Int): Deferred<Boolean> = CoroutineScope(Dispatchers.IO).async {
        var existe: Boolean = true
        Log.d("DebugLog", "lanzarPeticion iniciada")
        // Lanza la corrutina en segundo plano
        CoroutineScope(Dispatchers.IO).launch {
            try {
                Log.d("DebugLog", "Dentro de la corrutina")
                // Petición HTTP para obtener el objeto módulo
                val moduleResponse: Response<Modulo> = ModuloRetrofit.apiService.getModuleId(id)
                Log.d("DebugLog", "Petición API realizada")
                var modulo: Modulo?
                if (moduleResponse.isSuccessful) {
                    Log.d("DebugLog", "Respuesta exitosa")
                    // Se obtiene el objeto modulo del body del objeto Response
                    modulo = moduleResponse.body()
                } else {
                    Log.e("ErrorLog", "Respuesta fallida, código de error: ${moduleResponse.code()}")
                    when (moduleResponse.code()) {
                        404 -> {
                            //Si salta el 404 es que no ha encontrado ningún módulo con ese id
                            existe = false
                        }

                        500 -> showInternalServerError()
                        else -> showGeneralError()
                    }
                }
            } catch (e: Exception) {
                Log.e("ErrorLog", "Excepción en lanzarPeticion: ${e.message}")
            }
        }
        //Retorno si existe o no
        return@async existe
    }

    private fun lanzarPost(id: Int, nombre: String, profesor: String, nota: Int) {
        Log.d("DebugLog", "lanzarPost iniciada")
        CoroutineScope(Dispatchers.IO).launch {
            try {
                Log.d("DebugLog", "Dentro de la corrutina")
                // Creo un objeto Modulo con los datos del usuario
                var moduloNuevo: Modulo = Modulo(id.toString(), nombre, nota, profesor)
                //Hago el post
                ModuloRetrofit.apiService.insertModule(moduloNuevo)
                Log.d("DebugLog", "Post API realizada")
            } catch (e: Exception) {
                Log.e("ErrorLog", "Excepción en lanzarPeticion: ${e.message}")
            }
        }
    }

    private fun limpiarCampos() {
        inputId.setText("")
        inputNombre.setText("")
        inputProfesor.setText("")
        inputNota.setText("")
    }

    //ERRORES======================================================================================
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