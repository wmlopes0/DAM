/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t06ejercicio04;

/**
 *
 * @author Walter
 */
public class T06Ejercicio04 {

    public static void rellenarArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = (int) Math.round((Math.random() * 100 + 1) + 100);
            }
        }
    }

    public static void mostrarArray(int[][] array) {
        System.out.println("================ARRAY===============");
        for (int i = 0; i < array.length; i++) {
            System.out.println("\n------------------------------------");
            for (int j = 0; j < array[i].length; j++) {
                System.out.print("P[" + i + "," + j + "]" + "-" + array[i][j] + "   ");

            }
        }
        System.out.println("\n------------------------------------");
    }

    public static void mostrarPares(int[][] array) {
        System.out.println("\n================PARES===============");

        for (int i = 0; i < array.length; i++) {

            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] % 2 == 0) {
                    System.out.println("Posicion[" + i + "," + j + "]" + "-" + array[i][j]);
                }

            }
        }
        System.out.println("====================================");
    }

    public static void main(String[] args) {
        int[][] array = new int[4][3];
        rellenarArray(array);
        mostrarArray(array);
        mostrarPares(array);
    }

}
