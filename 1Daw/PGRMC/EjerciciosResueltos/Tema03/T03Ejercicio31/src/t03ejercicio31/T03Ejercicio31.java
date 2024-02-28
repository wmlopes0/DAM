/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t03ejercicio31;

/**
 *
 * @author Walter
 */
public class T03Ejercicio31 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int numAleatorio, contadorImpar = 0, contador = 0;

        System.out.println("Los tres numeros impares generados son: ");

        do {
            numAleatorio = (int) Math.floor(Math.random() * 100);

            contador++;

            if (numAleatorio % 2 != 0) {
                contadorImpar++;
                System.out.println(numAleatorio);
            }

        } while (contadorImpar != 3);

        System.out.println("El numero de valores aleatorios necesarios han sido: " + contador);
    }

}
