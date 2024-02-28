/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t06ejercicio03;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class T06Ejercicio03 {

    public static void rellenarArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) Math.round(Math.random() * 6 + 1);
        }
    }

    public static void mostrarArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println("Posicion " + (i + 1) + ": " + array[i]);
        }
    }

    public static int pedirLongitud() {
        Scanner entrada = new Scanner(System.in);
        int longitud = 0;
        boolean error;
        do {
            error = false;
            try {
                System.out.println("Por favor, introduzca una longitud para el vector del 1 al 10:");
                longitud = entrada.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: Has introducido una letra.Por favor, intentelo de nuevo.");
                error = true;
                entrada.next();
            }
            if (error == false && longitud < 1 || longitud > 10) {
                System.out.println("Error: Solo son validos los numeros del 1 al 10.Por favor, intentelo de nuevo.");
                error = true;
            }
        } while (error);

        return longitud;
    }

    public static void main(String[] args) {
        int[] array = new int[pedirLongitud()];
        rellenarArray(array);
        mostrarArray(array);
    }
}
