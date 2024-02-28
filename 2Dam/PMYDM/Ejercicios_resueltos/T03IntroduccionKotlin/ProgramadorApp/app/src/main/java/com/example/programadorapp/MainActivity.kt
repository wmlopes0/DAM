package com.example.programadorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    val TAG = "EJERCICIO"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var programador1: Programador = Programador()
        programador1.getProgrammerData()
    }
}

class Programador() : ProgramadorInterface {
    override fun getProgrammerData(): ProgrammerData {
        var walter: ProgrammerData = ProgrammerData(getName(), getEdad(), getLanguage())
        return walter
    }

    private fun getName(): String = "Walter"
    private fun getEdad(): Int = 25
    private fun getLanguage(): String = "Kotlin"
}

interface ProgramadorInterface {
    fun getProgrammerData(): ProgrammerData
}

data class ProgrammerData(
    var name: String,
    var age: Int,
    var language: String
)