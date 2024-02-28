package com.example.t05ejercicio05.ui.gato

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GatoViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Gato Fragment"
    }
    val text: LiveData<String> = _text
}