/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebacomplejidadciclomatica;

/**
 *
 * @author Walter
 */
public class Burbuja {

    public boolean burbuja(int[] arrayNumeros) {
        boolean ordenado = true;
        int elementoActual, elementoSiguiente;

        for (int x = 0; x < arrayNumeros.length; x++) {             //NODO 1
            for (int y = 0; y < arrayNumeros.length - 1; y++) {         //NODO 2

                elementoActual = arrayNumeros[y];                   //NODO 3
                elementoSiguiente = arrayNumeros[y + 1];            //NODO 3

                if (elementoActual > elementoSiguiente) {           //NODO 4
                    //Intercambiar
                    arrayNumeros[y] = elementoSiguiente;            //NODO 5
                    arrayNumeros[y + 1] = elementoActual;           //NODO 5
                    ordenado = false;                               //NODO 5
                }
            }
        }
        return ordenado;                                            //NODO 6
    }

    public int suma(int num1, int num2) {
        return num1 + num2;
    }

}
