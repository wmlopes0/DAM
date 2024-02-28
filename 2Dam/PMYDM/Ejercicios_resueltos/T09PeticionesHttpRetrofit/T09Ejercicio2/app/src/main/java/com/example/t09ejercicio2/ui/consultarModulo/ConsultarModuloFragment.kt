package com.example.t09ejercicio2.ui.consultarModulo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.t09ejercicio2.R
import com.example.t09ejercicio2.databinding.FragmentConsultarModuloBinding
import com.example.t09ejercicio2.model.Modulo
import com.example.t09ejercicio2.service.ModuloRetrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class ConsultarModuloFragment : Fragment() {

    //VARIABLES GLOBALES==============
    private var _binding: FragmentConsultarModuloBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    private lateinit var inputId: EditText
    private lateinit var botonConsultar: Button
    private lateinit var nombreValue: TextView
    private lateinit var profesorValue: TextView
    private lateinit var notaValue: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConsultarModuloBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //LÓGICA==========================
        //Recuperamos los componentes
        inputId = root.findViewById(R.id.inputId)
        botonConsultar = root.findViewById(R.id.buttonConsultar)
        nombreValue = root.findViewById(R.id.nombreValue)
        profesorValue = root.findViewById(R.id.profesorValue)
        notaValue = root.findViewById(R.id.notaValue)

        //Acción botón consultar
        botonConsultar.setOnClickListener {
            //Recupero el valor del input
            var id: Int? = inputId.text.toString().toIntOrNull()
            if (id != null) {
                lanzarPeticion(id)//Lanzamos la petición
            } else {
                Toast.makeText(requireContext(), getText(R.string.toastIdNulo), Toast.LENGTH_SHORT).show()
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
                // Petición HTTP para obtener el objeto módulo
                val moduleResponse: Response<Modulo> = ModuloRetrofit.apiService.getModuleId(id)
                Log.d("DebugLog", "Petición API realizada")
                var modulo: Modulo?
                if (moduleResponse.isSuccessful) {
                    Log.d("DebugLog", "Respuesta exitosa")
                    // Se obtiene el objeto modulo del body del objeto Response
                    modulo = moduleResponse.body()

                    if (modulo != null) {
                        mostrarDatos(modulo)//Seteo los datos
                    } else {
                        Log.d("DebugLog", "Objeto módulo es nulo")
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

    private fun mostrarDatos(modulo: Modulo?) {
        GlobalScope.launch(Dispatchers.Main) {
            nombreValue.setText(modulo?.nombre)
            profesorValue.setText(modulo?.profesor)
            notaValue.setText(modulo?.nota.toString())
        }
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