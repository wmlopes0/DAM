/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t03ejercicio29;

import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class T03Ejercicio29 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        int contador = 0, numUsuario;
        int numAleatorio = (int) Math.floor(Math.random() * 100);

        do {
            System.out.println("Por favor, introduzca un numero: ");
            numUsuario = entrada.nextInt();

            contador++;

            if (numUsuario < numAleatorio) {
                System.out.println("El numero que tienes que adivinar es MAYOR");
                System.out.println("················································");
            } else {
                System.out.println("El numero que tienes que adivinar es MENOR");
                System.out.println("················································");
            }

        } while (numUsuario != numAleatorio);

        System.out.println("¡ENHORABUENA! Has adivinado el numero, el numero de intentos ha sido: " + contador);
    }

}
