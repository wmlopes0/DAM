/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t04ejercicio02;

import java.util.Scanner;

/**
 *
 * @author wmartinl01
 */
public class T04Ejercicio02 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        int num1, num2;

        System.out.print("Por favor, introduzca un numero:");
        num1 = entrada.nextInt();

        System.out.print("Por favor, introduzca un segundo numero:");
        num2 = entrada.nextInt();
        
        if (num1>10) {
            Operaciones.multiplicar(num1,num2);
        } else {
            Operaciones.sumar(num1,num2);
        }

    }
}
