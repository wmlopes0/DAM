/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t06ejercicio02;

import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class T06Ejercicio02 {

    public static void rellenarArray(int[] array) {
        Scanner entrada = new Scanner(System.in);
        for (int i = 0; i < array.length; i++) {
            System.out.println("Introduzca un numero para la posicion " + (i + 1) + ": ");
            array[i] = entrada.nextInt();
        }
        System.out.println("----------------------------------");
    }

    public static void mostrarArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println("Posicion " + i + ": " + array[i]);
        }
    }

    public static int[] intercambiarPosicion(int[] array) {
        //Intercambio posicion 2 y 4
        int aux = array[2];
        array[2] = array[4];
        array[4] = aux;
        return array;
    }

    public static void main(String[] args) {
        int[] array = new int[7];
        System.out.println("------------ARRAY CREADO------------");
        rellenarArray(array);
        System.out.println("-----------MUESTRO ARRAY-----------");
        mostrarArray(array);
        System.out.println("-----------INTERCAMBIO POSICION 2 Y 4-----------");
        mostrarArray(intercambiarPosicion(array));
    }

}
