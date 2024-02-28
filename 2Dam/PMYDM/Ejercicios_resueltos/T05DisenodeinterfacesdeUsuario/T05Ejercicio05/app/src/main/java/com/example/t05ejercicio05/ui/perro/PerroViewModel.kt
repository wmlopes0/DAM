package com.example.t05ejercicio05.ui.perro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PerroViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Perro Fragment"
    }
    val text: LiveData<String> = _text
}