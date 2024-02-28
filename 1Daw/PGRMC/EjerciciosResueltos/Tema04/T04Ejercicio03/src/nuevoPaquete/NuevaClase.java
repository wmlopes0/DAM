/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nuevoPaquete;

/**
 *
 * @author wmartinl01
 */
public class NuevaClase {

    public static void mostrarMayor(int a, int b, int c) {
        if (a > b && a > c) {
            System.out.println("El número mayor de los introducidos es el " + a);
        } else {
            if (b > a && b > c) {
                System.out.println("El número mayor de los introducidos es el " + b);
            } else {
                System.out.println("El número mayor de los introducidos es el " + c);
            }
        }
    }
}
