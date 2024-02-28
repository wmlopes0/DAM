package com.example.miprimertabbedactivity.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.miprimertabbedactivity.R
import com.example.miprimertabbedactivity.databinding.FragmentTabbed2Binding
import com.example.miprimertabbedactivity.databinding.FragmentTabbedBinding

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment2 : Fragment() {

//    private lateinit var pageViewModel: PageViewModel
    private var _binding: FragmentTabbed2Binding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
//            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTabbed2Binding.inflate(inflater, container, false)
        val root = binding.root

        val textView: TextView = binding.sectionLabel
//        pageViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(): PlaceholderFragment2 {
//            return PlaceholderFragment2().apply {
//                arguments = Bundle().apply {
//                    putInt(ARG_SECTION_NUMBER, sectionNumber)
//                }
//            }
            return PlaceholderFragment2()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}