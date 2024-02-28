/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t06ejercicio06;

/**
 *
 * @author Walter
 */
public class T06Ejercicio06 {

    public static void rellenarArray(int[] array) {
        int numero = 1;
        boolean primo = true;
        for (int i = 0; i < array.length; i++) {
            do {
                primo = true;
                for (int j = 2; j < numero; j++) {
                    if (numero % j == 0) {
                        primo = false;
                    }
                }
                if (primo == false) {
                    numero++;
                }
            } while (!primo);

            array[i] = numero;
            numero++;
        }

    }

    public static void mostrarArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println("Posicion " + (i + 1) + ": " + array[i]);
        }
    }

    public static void main(String[] args) {
        int[] array = new int[80];
        rellenarArray(array);
        mostrarArray(array);
    }

}
