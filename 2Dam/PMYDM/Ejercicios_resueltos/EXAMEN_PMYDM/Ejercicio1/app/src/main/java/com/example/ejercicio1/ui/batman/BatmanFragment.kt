package com.example.ejercicio1.ui.batman

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.ejercicio1.R
import com.example.ejercicio1.databinding.FragmentBatmanBinding

class BatmanFragment : Fragment() {

    //Componentes
    private lateinit var imageButton: ImageButton
    private lateinit var textView: TextView
    private lateinit var radioGroup: RadioGroup

    private var _binding: FragmentBatmanBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBatmanBinding.inflate(inflater, container, false)
        val view: View = binding.root

        //FUNCIONALIDAD FRAGMENT BATMAN===============================

        //Recupero los componentes
        imageButton = view.findViewById(R.id.imageButton)
        radioGroup = view.findViewById(R.id.radioGroupVillanos)
        textView = view.findViewById(R.id.textViewVillano)

        //Si el usuario hace click en el botón lanza la función
        imageButton.setOnClickListener {
            elegirVillano()
        }

        return view
    }

    //Funcion elegir villano
    private fun elegirVillano() {
        //Recupero el radioButton seleccionado
        var seleccionado: Int = radioGroup.checkedRadioButtonId

        when (seleccionado) {
            -1 -> {
                Toast.makeText(
                    this.context,
                    getString(R.string.toast_noSeleccionado),
                    Toast.LENGTH_SHORT
                ).show()
            }

            R.id.radioButtonJoker -> {
                textView.setText(getString(R.string.cuidado_joker))
            }

            R.id.radioButtonEspanta -> {
                textView.setText(getString(R.string.cuidado_espantapajaros))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}