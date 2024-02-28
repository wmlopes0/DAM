package com.example.t09ejercicio2.ui.eliminarModulo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.t09ejercicio2.R
import com.example.t09ejercicio2.databinding.FragmentEliminarModuloBinding
import com.example.t09ejercicio2.databinding.FragmentListarModulosBinding
import com.example.t09ejercicio2.model.Modulo
import com.example.t09ejercicio2.service.ModuloRetrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EliminarModuloFragment : Fragment() {

    //VARIABLES GLOBALES
    private var _binding: FragmentEliminarModuloBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    private lateinit var inputId: EditText
    private lateinit var botonEliminar: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEliminarModuloBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //LÓGICA==========================
        //Recupero los componentes
        inputId = root.findViewById(R.id.inputId)
        botonEliminar = root.findViewById(R.id.buttonEliminar)

        //Acción botón eliminar
        botonEliminar.setOnClickListener {
            eliminarModulo()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //========================================================================
    private fun eliminarModulo() {
        //Recupero el input controlando que no sea nulo
        var id: Int? = inputId.text.toString().toIntOrNull()
        if (id != null) {
            lanzarDelete(id)
            Toast.makeText(requireContext(), getText(R.string.toastDeleteCorrecto), Toast.LENGTH_SHORT)
                .show()
            limpiarCampos()
        } else {
            Toast.makeText(requireContext(), getText(R.string.toastIdNulo), Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun lanzarDelete(id: Int) {
        Log.d("DebugLog", "lanzarPost iniciada")
        CoroutineScope(Dispatchers.IO).launch {
            try {
                Log.d("DebugLog", "Dentro de la corrutina")
                //Lanzo el delete
                ModuloRetrofit.apiService.deleteModuleId(id)
                Log.d("DebugLog", "Post API realizada")
            } catch (e: Exception) {
                Log.e("ErrorLog", "Excepción en lanzarPeticion: ${e.message}")
            }
        }
    }

    private fun limpiarCampos() {
        inputId.setText("")
    }
}