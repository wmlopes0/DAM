/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t06ejercicio08;

import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class T06Ejercicio08 {

    public static int pedirNumero() {
        Scanner entrada = new Scanner(System.in);
        int numero;
        do {
            System.out.print("Por favor, introduzca un numero de 5 cifras: ");
            numero = entrada.nextInt();
        } while (numero > 99999 || numero < 10000);
        return numero;
    }

    public static void rellenarArray(char[] array, int num) {
        String numero = String.valueOf(num);
        for (int i = 0; i < array.length; i++) {
            array[i] = numero.charAt(i);
        }
    }

    public static void mostrarArray(char[] array) {
        System.out.print("\nEl numero introducido escrito al reves es el: ");
        for (int i = (array.length-1); i >= 0; i--) {
            System.out.print(array[i]);
        }
        System.out.println("\n");
    }

    public static void main(String[] args) {
        char[] array = new char[5];
        rellenarArray(array, pedirNumero());
        mostrarArray(array);
    }

}
