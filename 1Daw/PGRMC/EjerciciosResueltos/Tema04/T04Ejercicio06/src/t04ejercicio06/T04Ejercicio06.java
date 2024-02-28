/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t04ejercicio06;

import java.util.Scanner;

/**
 *
 * @author wmartinl01
 */
public class T04Ejercicio06 {

    public static void mostrarNota(int nota) {
        if (nota >= 0 && nota <= 4) {
            System.out.println("Suspenso.");
        } else {
            if (nota >= 5 && nota <= 6) {
                System.out.println("Bien.");
            } else {
                if (nota >= 7 && nota <= 8) {
                    System.out.println("Notable.");
                } else {
                    System.out.println("Sobresaliente.");
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        int nota;

        System.out.println("Por favor, introduzca la nota del alumno: ");
        nota = entrada.nextInt();

        if (nota >= 0 && nota <= 10) {
            mostrarNota(nota);
        } else {
            System.out.println("ERROR: La nota introducida debe estar comprendida entre 0 y 10.");
        }
    }

}
