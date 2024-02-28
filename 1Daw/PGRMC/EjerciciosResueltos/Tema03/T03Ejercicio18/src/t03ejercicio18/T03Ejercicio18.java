/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t03ejercicio18;

import java.util.Scanner;

/**
 *
 * @author wmartinl01
 */
public class T03Ejercicio18 {

    final static int PASSWORD = 1234;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        int contrasena, contador=0;

        do {
            System.out.print("Por favor, introduzca la contraseña: ");
            contrasena = entrada.nextInt();
            
            if (contrasena == PASSWORD) {
                System.out.println("¡Enhorabuena! Contraseña correcta.");
            } else {
                contador++;
            }
            if (contador==3) {
                System.out.println("ERROR DE ACCESO");
            }
        } while (contrasena != PASSWORD && contador<3);

    }

}
