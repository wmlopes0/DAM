package com.example.t03ejercicio8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    val TAG:String = "Ejercicio8"
    var listadoPrimos:ArrayList<Int> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Ejercicio 08: Escribe un programa en Kotlin que rellene un listado con
//        los 80 primeros números primos. Posteriormente, muestra el listado
//        en la consola de Logcat.

        //Relleno el listado con los 80 primeros numeros primos
        rellenarListadoPrimos()
        //Muestro el listado de primos
        mostrarListadoPrimos()
    }

    private fun rellenarListadoPrimos(){
        var contadorPrimos:Int = 0
        var numero:Int = 1

        while (contadorPrimos != 80){
            if (esPrimo(numero)){
                listadoPrimos.add(numero)
                contadorPrimos++
            }
            numero++
        }
    }

    private fun esPrimo(num:Int):Boolean{
        var primo:Boolean = true
        for (a:Int in 2..num-1){
            if (num%a ==0){
                primo = false
            }
        }
        return primo
    }

    private fun mostrarListadoPrimos(){
        Log.w(TAG,listadoPrimos.joinToString("--"))
    }

}