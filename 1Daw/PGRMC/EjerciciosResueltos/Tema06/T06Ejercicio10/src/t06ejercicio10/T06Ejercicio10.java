/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t06ejercicio10;

/**
 *
 * @author Walter
 */
public class T06Ejercicio10 {

    public static void rellenarArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) Math.round(Math.random() * 7 + 1);
        }
    }

    public static void sustituirRepetidos(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array.length; j++) {
                if (i != j && array[i] == array[j]) {
                    array[i] = 0;
                    array[j] = 0;
                }
            }
        }
    }

    public static void mostrarArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

    public static void main(String[] args) {
        int[] array = new int[10];
        rellenarArray(array);
        System.out.print("Se han generado los siguientes numeros: ");
        mostrarArray(array);
        sustituirRepetidos(array);
        System.out.print("\nSustituimos los elementos repetidos x0: ");
        mostrarArray(array);
    }

}
