package com.example.t09practicaentregable.ui.eliminarLibro

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.t09practicaentregable.R
import com.example.t09practicaentregable.databinding.FragmentEliminarLibroBinding
import com.example.t09practicaentregable.service.LibroRetrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EliminarLibroFragment : Fragment() {

    //VARIABLES GLOBALES==========================================
    private var _binding: FragmentEliminarLibroBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    private lateinit var inputId: EditText
    private lateinit var botonEliminar: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEliminarLibroBinding.inflate(inflater, container, false)
        val root: View = binding.root
        //LÓGICA==================================================
        //Recupero los componentes
        inputId = root.findViewById(R.id.editTextInput)
        botonEliminar = root.findViewById(R.id.botonEliminar)
        //Acción botón eliminar
        botonEliminar.setOnClickListener {
            eliminarLibro()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //========================================================================
    private fun eliminarLibro() {
        //Recupero el input controlando que no sea nulo
        var id: Int? = inputId.text.toString().toIntOrNull()
        if (id != null) {
            dialogoConfirmacionEliminar(id)
        } else {
            Toast.makeText(requireContext(), getText(R.string.toastIdNulo), Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun dialogoConfirmacionEliminar(id: Int) {
        //Creamos cuadroDialogo
        val cuadroDialogo: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        //Establecemos título y mensaje
        cuadroDialogo.setTitle("Confirmación")
        cuadroDialogo.setMessage("¿Desea eliminar el libro con ID $id?")

        //Configuramos el positiveButton
        cuadroDialogo.setPositiveButton("SI") { dialog, which ->
            //Elimino solo si confirma
            lanzarDelete(id)
            Toast.makeText(requireContext(), getText(R.string.toastDeleteCorrecto), Toast.LENGTH_SHORT)
                .show()
            limpiarCampos()
            dialog.cancel()
        }

        //Configuramos el negativeButton
        cuadroDialogo.setNegativeButton("NO") { dialog, which ->
            dialog.cancel()
        }

        //Mostramos
        cuadroDialogo.show()
    }

    private fun lanzarDelete(id: Int) {
        Log.d("DebugLog", "lanzarPost iniciada")
        CoroutineScope(Dispatchers.IO).launch {
            try {
                Log.d("DebugLog", "Dentro de la corrutina")
                //Lanzo el delete
                LibroRetrofit.apiService.deleteLibroId(id)
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