/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t06ejercicio11;

/**
 *
 * @author Walter
 */
public class T06Ejercicio11 {

    public static void rellenarArray(int[] array) {
        boolean repetido;
        for (int i = 0; i < array.length; i++) {
            do {
                repetido = false;
                array[i] = (int) Math.round(Math.random() * 9);
                for (int j = 0; j < array.length; j++) {
                    if (i != j && array[i] == array[j]) {
                        repetido = true;
                    }
                }
            } while (repetido);
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
        System.out.print("Se ha generado el siguiente array: ");
        mostrarArray(array);
    }

}
