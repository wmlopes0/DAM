package com.example.t03ejercicio6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    val TAG:String = "EJERCICIO6"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Crea un programa en Kotlin que almacene en una
//        variable la nota de un alumno (número entero entre 0 y 10) y muestre
//        en la consola de Logcat su calificación según el valor de la nota
//        almacenada:
//        • 0 a 4 = Suspenso.
//        • 5 a 6 = Bien.
//        • 7 a 8 = Notable.
//        • 9 a 10 = Sobresaliente

        var numero:Int = Random.nextInt(1,11) //Este genera un número entero aleatorio del 1 al 10

        when(numero){
            in 0..4 -> Log.w(TAG,"La nota es $numero y la calificación es SUSPENSO.")
            in 5..6 -> Log.w(TAG,"La nota es $numero y la calificación es BIEN.")
            in 7..8 -> Log.w(TAG,"La nota es $numero y la calificación es NOTABLE.")
            in 9..10 -> Log.w(TAG,"La nota es $numero y la calificación es SOBRESALIENTE.")
        }

    }
}