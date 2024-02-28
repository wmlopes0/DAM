/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t03ejercicio15;

import java.util.Scanner;

/**
 *
 * @author wmartinl01
 */
public class T03Ejercicio15 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        //Declaro variables
        int num, resultado;

        //Pido el número al usuario para calcular la tabla de multiplicar
        System.out.print("Por favor, introduzca un número para calcular su tabla de multiplicar: ");
        num = entrada.nextInt();

        //Utilizo un bucle for para calcular la tabla de multiplicar y mostrarla
        for (int i = 0; i <= 10; i++) {
            resultado = num * i;
            System.out.println(num + " x " + i + " = " + resultado);
        }

    }

}
