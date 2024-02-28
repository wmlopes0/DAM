/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t06ejercicio05;

import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class T06Ejercicio05 {

    public static void rellenarArray(int[][] array) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("==========RELLENA EL ARRAY==========");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print("Posicion [" + i + "," + j + "]: ");
                array[i][j] = entrada.nextInt();
            }
        }
        System.out.println("====================================");
    }

    public static void mostrarArray(int[][] array) {
        System.out.println("================ARRAY===============");
        for (int i = 0; i < array.length; i++) {
            System.out.println("\n----------------------");
            for (int j = 0; j < array[i].length; j++) {
                System.out.print("P[" + i + "," + j + "]" + "-" + array[i][j] + "   ");

            }
        }
        System.out.println("\n----------------------");
    }

    public static int sumaArray(int[][] array) {
        int suma = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                suma += array[i][j];
            }
        }
        return suma;
    }

    public static int menorArray(int[][] array) {
        int menor = array[0][0];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] < menor) {
                    menor = array[i][j];
                }
            }
        }
        return menor;
    }

    public static int mayorArray(int[][] array) {
        int mayor = array[0][0];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] > mayor) {
                    mayor = array[i][j];
                }
            }
        }
        return mayor;
    }

    public static void main(String[] args) {
        int[][] array = new int[4][2];
        rellenarArray(array);
        mostrarArray(array);
        System.out.println("==============RESULTADO=============");
        System.out.println("El mayor es: " + mayorArray(array));
        System.out.println("El menor es: " + menorArray(array));
        System.out.println("La suma total es: " + sumaArray(array));

    }

}
