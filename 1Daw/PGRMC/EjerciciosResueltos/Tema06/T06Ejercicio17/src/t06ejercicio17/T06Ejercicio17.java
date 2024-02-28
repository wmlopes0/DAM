/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t06ejercicio17;

/**
 *
 * @author Walter
 */
public class T06Ejercicio17 {

    public static void rellenarArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) Math.round(Math.random() * 9);
        }
    }

    public static void mostrarArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "  ");
        }
    }

    public static void ordenarArray(int[] array) {
        int aux;
        for (int i = 0; i < (array.length - 1); i++) {
            for (int j = 0; j < (array.length - 1); j++) {
                if (array[j] < array[j + 1]) {
                    aux = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = aux;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[10];
        rellenarArray(array);
        mostrarArray(array);
        ordenarArray(array);
        System.out.println("");
        mostrarArray(array);
    }

}
