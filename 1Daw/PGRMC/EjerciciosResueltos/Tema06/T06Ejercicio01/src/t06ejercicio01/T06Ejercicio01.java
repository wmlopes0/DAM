/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t06ejercicio01;

import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class T06Ejercicio01 {
    
    public static void rellenarArray(int[] array) {
        Scanner entrada = new Scanner(System.in);
        for (int i = 0; i < array.length; i++) {
            System.out.println("Introduzca un numero para la posicion " + (i + 1) + ": ");
            array[i] = entrada.nextInt();
        }
        System.out.println("----------------------------------");
    }
    
    public static void mostrarPares(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                System.out.println(array[i]);
            }
        }
    }
    
    public static void main(String[] args) {
        int[] array = new int[10];
        System.out.println("-----------ARRAY CREADO-----------");
        rellenarArray(array);
        System.out.println("A continuacion, se mostraran los numeros pares que contenga el array: ");
        mostrarPares(array);
    }
    
}
