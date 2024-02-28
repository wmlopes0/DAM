/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t03ejercicio16;

/**
 *
 * @author wmartinl01
 */
public class T03Ejercicio16 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int num = 20, contador = 0;
        System.out.println("Los números impares existentes entre el número 20 y el 160 son: ");

        while (num <= 160) {
            if (num % 2 != 0) {
                System.out.print(num + "-");
                contador++;
            }
            num++;
        }
        System.out.println("\n La cantidad de números impares impresos han sido: " + contador);
    }

}
