/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t03ejercicio22;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author wmartinl01
 */
public class T03Ejercicio22 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        int num1, num2,resultado;
       

        try {
            System.out.print("Por favor, introduzca el primer numero: ");
            num1 = entrada.nextInt();

            System.out.print("Por favor, introduzca el segundo numero: ");
            num2 = entrada.nextInt();

            resultado = num1 + num2;

            System.out.println("El resultado de sumar " + num1 + " y " + num2 + " es: " + resultado);

        } catch (InputMismatchException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

}
