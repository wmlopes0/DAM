/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t03ejercicio27;

import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class T03Ejercicio27 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int num1, num2, opcion;
        float resultado;

        System.out.print("Por favor, introduzca un numero: ");
        num1 = entrada.nextInt();

        System.out.print("Por favor, introduzca un segundo numero: ");
        num2 = entrada.nextInt();

        do {

            System.out.println("----------MENU----------");
            System.out.println("1.- Sumar los numeros.");
            System.out.println("2.- Restar los numeros.");
            System.out.println("3.- Multiplicar los numeros.");
            System.out.println("4.- Dividir los numeros.");
            System.out.println("5.- Salir del programa.");
            System.out.println("-------------------------");
            System.out.print("A continuación, elija una opcion: ");
            opcion = entrada.nextInt();

            switch (opcion) {
                case 1:
                    resultado = num1 + num2;
                    System.out.println("El resultado es: " + resultado);
                    break;
                case 2:
                    resultado = num1 - num2;
                    System.out.println("El resultado es: " + resultado);
                    break;
                case 3:
                    resultado = num1 * num2;
                    System.out.println("El resultado es: " + resultado);
                    break;
                case 4:
                    try {
                        resultado = num1 / num2;
                        System.out.println("El resultado es: " + resultado);
                    } catch (ArithmeticException ex) {
                        System.out.println("ERROR: " + ex.getMessage());
                    }
                    break;
                case 5:
                    break;
                default:
                    System.out.println("ERROR: Por favor, introduzca un numero comprendido entre el 1 y el 5.");
            }

        } while (opcion != 5);

    }

}
