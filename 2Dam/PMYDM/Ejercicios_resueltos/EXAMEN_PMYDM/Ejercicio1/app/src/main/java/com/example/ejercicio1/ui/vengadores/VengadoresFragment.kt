package com.example.ejercicio1.ui.vengadores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.fragment.app.Fragment
import com.example.ejercicio1.R
import com.example.ejercicio1.databinding.FragmentVengadoresBinding

class VengadoresFragment : Fragment() {

    //Componentes
    private lateinit var checkBoxHulk: CheckBox
    private lateinit var checkBoxThor: CheckBox
    private lateinit var toggleButton: ToggleButton
    private lateinit var imageView: ImageView
    private var enviadosAMision: Boolean = false


    private var _binding: FragmentVengadoresBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentVengadoresBinding.inflate(inflater, container, false)
        val view: View = binding.root

        //FUNCIONALIDAD FRAGMENT VENGADORES =============================

        //Recupero los componentes
        checkBoxHulk = view.findViewById(R.id.checkBoxHulk)
        checkBoxThor = view.findViewById(R.id.checkBoxThor)
        toggleButton = view.findViewById(R.id.toggleButton)
        imageView = view.findViewById(R.id.imageView)

        //Si el usuario hace click en el toggleButton
        toggleButton.setOnCheckedChangeListener { buttonView, isChecked ->
            if (!enviadosAMision) {
                enviarAMision()
                enviadosAMision = true
            } else {
                retirada()
                enviadosAMision = false
            }
        }
        return view
    }

    //Función enviar a misión
    private fun enviarAMision() {
        var hulkSeleccionado: Boolean = checkBoxHulk.isChecked
        var thorSeleccionado: Boolean = checkBoxThor.isChecked

        when {
            //Si están los dos seleccionados
            thorSeleccionado && hulkSeleccionado -> {
                //Lanzo el Toast
                Toast.makeText(
                    this.context,
                    getString(R.string.toast_vengadoresSeleccionados),
                    Toast.LENGTH_SHORT
                ).show()
                //Actualizo la imagen
                imageView.setImageResource(R.drawable.vengadores)
            }

            hulkSeleccionado && !thorSeleccionado -> {
                //Lanzo el Toast
                Toast.makeText(
                    this.context,
                    getString(R.string.toast_hulk),
                    Toast.LENGTH_SHORT
                ).show()
                //Actualizo la imagen
                imageView.setImageResource(R.drawable.hulk)
            }

            !hulkSeleccionado && thorSeleccionado -> {
                //Lanzo el Toast
                Toast.makeText(
                    this.context,
                    getString(R.string.toast_thor),
                    Toast.LENGTH_SHORT
                ).show()
                //Actualizo la imagen
                imageView.setImageResource(R.drawable.thor)
            }

            else -> {
                //Lanzo el Toast
                Toast.makeText(
                    this.context,
                    getString(R.string.toast_vengadoresSinSeleccionar),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    //Función retirada
    private fun retirada() {
        //Lanzo el Toast
        Toast.makeText(
            this.context,
            getString(R.string.toast_retirada),
            Toast.LENGTH_SHORT
        ).show()
        //Actualizo la imagen
        imageView.setImageResource(R.drawable.thanos)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}