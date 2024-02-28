/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t04ejercicio04;

/**
 *
 * @author wmartinl01
 */
public class ClaseDistinta {
    public static void mostrarMenor(int a, int b, int c) {
        if (a < b && a < c) {
            System.out.println("El número menor de los introducidos es el " + a);
        } else {
            if (b < a && b < c) {
                System.out.println("El número menor de los introducidos es el " + b);
            } else {
                System.out.println("El número menor de los introducidos es el " + c);
            }
        }
    }
}
