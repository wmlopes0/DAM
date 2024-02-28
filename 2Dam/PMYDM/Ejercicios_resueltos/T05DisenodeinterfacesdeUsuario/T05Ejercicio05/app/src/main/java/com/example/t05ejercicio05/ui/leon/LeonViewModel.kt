package com.example.t05ejercicio05.ui.leon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LeonViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is León Fragment"
    }
    val text: LiveData<String> = _text
}