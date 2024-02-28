package com.example.t03ejercicio7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    val TAG:String = "EJERCICIO7"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Ejercicio 07: Diseña un programa en Kotlin que almacene un número del 1 al 4
//        en una variable (opción) y dos números enteros (num1 y num2).
//        Posteriormente el programa, en función de la opción almacenada, realizará las
//        siguientes operaciones:
//        • 1 – Sumar num1 y num2.
//        • 2 – Restar num1 y num2.
//        • 3 – Multiplicar num1 y num2.
//        • 4 – Dividir num1 entre num2.
//        • Nota: Implementa cada una de las operaciones anteriores en una función

        var opcion:Int = Random.nextInt(1,5)
        var num1:Int = Random.nextInt(1,100)
        var num2:Int = Random.nextInt(1,100)

        when(opcion){
            1-> sumar(opcion,num1,num2)
            2-> restar(opcion,num1,num2)
            3-> multiplicar(opcion,num1,num2)
            4-> dividir(opcion,num1,num2)
        }
    }

    private fun sumar(opcion:Int,num1:Int,num2:Int){
        var resultado:Int = num1 + num2
        Log.w(TAG,"La opción elegida es $opcion. El resultado de sumar $num1 y $num2 es $resultado")
    }
    private fun restar(opcion:Int,num1:Int,num2:Int){
        var resultado:Int = num1 - num2
        Log.w(TAG,"La opción elegida es $opcion. El resultado de restar $num1 y $num2 es $resultado")
    }
    private fun multiplicar(opcion:Int,num1:Int,num2:Int){
        var resultado:Int = num1 * num2
        Log.w(TAG,"La opción elegida es $opcion. El resultado de multiplicar $num1 y $num2 es $resultado")
    }
    private fun dividir(opcion:Int,num1:Int,num2:Int){
        var resultado:Int = num1 / num2
        Log.w(TAG,"La opción elegida es $opcion. El resultado de dividir $num1 y $num2 es $resultado")
    }
}