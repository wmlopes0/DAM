package com.example.miprimeraapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    val TAG: String = "EJERCICIO";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        //Ejercicio 4
//        var num1:Int = 7
//        var num2:Int = 8
//        var num3:Int = 23
//
//        mostrarNumeros(num1,num2,num3)
//        sumarNumeros(num1,num2,num3)
//        Log.w(TAG,"La media de los numeros es ${calcularMedia(num1,num2,num3)}")
//        operaciones(calcularMedia(num1,num2,num3),num3)

    }

    fun mostrarNumeros(num1:Int,num2:Int,num3:Int){
        Log.w(TAG,"Los números son $num1,$num2 y $num3")
    }
    fun sumarNumeros(num1:Int,num2:Int,num3:Int){
        var suma:Int = num1+num2+num3
        Log.w(TAG,"La suma de los numeros es $suma")
    }
    fun calcularMedia(num1:Int,num2:Int,num3:Int):Int{
        var media:Int = (num1+num2+num3)/3
        return media
    }
    fun operaciones (media:Int,num3:Int){
        var mediaMultiplicada:Int = media*num3
        Log.w(TAG,"La media de los tres números multiplicada por $num3 es $mediaMultiplicada")
    }
}
//        EJERCICIOS ===============================
        //Ejercicio 1
//        var asignatura1 = "PMYDM"
//        var nota1 = 8.5
//        var asignatura2 = "DI"
//        var nota2 = 6.5
//        var notaMedia = (nota1+nota2)/2
//        Log.i(TAG,"Asignatura: $asignatura1")
//        Log.i(TAG,"Nota: $nota1")
//        Log.i(TAG,"Asignatura: $asignatura2")
//        Log.i(TAG,"Nota: $nota2")
//        Log.i(TAG,"NOTA MEDIA: $notaMedia")

      //Ejercicio 2
//        var cartera: Float = 130f
//        var billete50: Float
//        var billete10: Float
//        billete50 = cartera / 50
//        billete10 = (cartera%50)/10
//        Log.i(TAG,"$cartera euros hacen un total de: ${billete50.roundToInt()} billetes de 50 euros y ${billete50.roundToInt()} billetes de 10 euros.")


//        Ejercicio 3
//        var name: String = "Walter"
//        var password: String? = "holi"
//
//        password?.let{
//            Log.d(TAG,"Bienvenido $name")
//            Log.d(TAG,"El password introducido es $password")
//        }?:run{
//            Log.d(TAG,"La variable nombre no tiene valor o tiene un valor nulo")
//        }
//        //Actualizo la variable a null para ver el otro resultado
//        password = null
//
//        password?.let{
//            Log.d(TAG,"Bienvenido $name")
//            Log.d(TAG,"El password introducido es $password")
//        }?:run{
//            Log.d(TAG,"La variable nombre no tiene valor o tiene un valor nulo")
//        }


