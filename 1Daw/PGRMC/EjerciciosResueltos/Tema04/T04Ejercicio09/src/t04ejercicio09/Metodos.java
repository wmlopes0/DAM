/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t04ejercicio09;

import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Metodos {

    public static int pedirNum1() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Por favor, introduzca el primer numero: ");
        int num1 = entrada.nextInt();

        return num1;
    }

    public static int pedirNum2() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Por favor, introduzca el segundo numero: ");
        int num2 = entrada.nextInt();

        return num2;
    }

    public static int pedirNum3() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Por favor, introduzca el tercero numero: ");
        int num3 = entrada.nextInt();

        return num3;
    }

    public static void mostrarMenor(int num1, int num2, int num3) {
        int menor;
        if (num1 < num2 && num1 < num3) {
            menor = num1;
        } else {
            if (num2 < num1 && num2 < num3) {
                menor = num2;
            } else {
                menor = num3;
            }
        }

        System.out.println("El numero menor es: " + menor);
    }

}
