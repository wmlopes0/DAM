/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t03ejercicio09;

import java.util.Scanner;

/**
 *
 * @author wmartinl01
 */
public class T03Ejercicio09 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        //Declaro variables
        int aux, num1, num2, num3, num4, contador;

        //Pido los 4 numeros
        System.out.println("Por favor, indique el primer numero:");
        num1 = entrada.nextInt();

        System.out.println("Por favor, indique el segundo numero:");
        num2 = entrada.nextInt();

        System.out.println("Por favor, indique el tercer numero:");
        num3 = entrada.nextInt();

        System.out.println("Por favor, indique el cuarto numero:");
        num4 = entrada.nextInt();

        //Los ordeno mediante el método de la burbuja, tengo que repetir el ciclo 3 veces
        for (contador = 3; contador >= 0; contador--) {
            if (num1 > num2) {
                aux = num1;
                num1 = num2;
                num2 = aux;
            }
            if (num2 > num3) {
                aux = num2;
                num2 = num3;
                num3 = aux;
            }
            if (num3 > num4) {
                aux = num3;
                num3 = num4;
                num4 = aux;
            }
        }
        //Muestro por pantalla los números ordenados
        System.out.println("El orden de los numeros introducidos es el: " + num1 + " - " + num2 + " - " + num3 + " - " + num4);
    }

}
