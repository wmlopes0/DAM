package com.example.t05anexoejercicio02

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentSumador.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentSumador : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Recupero los editText y el boton
        val textNumber1: EditText? = view?.findViewById(R.id.editTextNumber)
        val textNumber2: EditText? = view?.findViewById(R.id.editTextNumber2)
        val botonSumar: Button? = view?.findViewById(R.id.botonSumar)

        botonSumar?.setOnClickListener {
            val num1: Int? = textNumber1?.text.toString().toIntOrNull()
            val num2: Int? = textNumber2?.text.toString().toIntOrNull()
            Toast.makeText(requireActivity(), "La suma de los dos números es ${num1!! + num2!!}", Toast.LENGTH_SHORT)
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sumador, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentSumador.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentSumador().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}