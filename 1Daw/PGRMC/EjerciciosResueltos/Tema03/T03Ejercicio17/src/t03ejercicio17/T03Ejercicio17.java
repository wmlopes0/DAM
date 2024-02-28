/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t03ejercicio17;

import java.util.Scanner;

/**
 *
 * @author wmartinl01
 */
public class T03Ejercicio17 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        int numEntrada;

        do {
            System.out.print("Por favor, introduzca un numero: ");
            numEntrada = entrada.nextInt();
            if (numEntrada < 0) {
                System.out.println("ERROR: Por favor, introduzca un numero mayor a 0.");
            }
        } while (numEntrada < 0);

        System.out.println("La raiz cuadrada de " + numEntrada + " es: " + Math.sqrt(numEntrada));
    }

}
