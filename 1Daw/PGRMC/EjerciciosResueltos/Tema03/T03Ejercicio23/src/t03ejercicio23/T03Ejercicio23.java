/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t03ejercicio23;

import java.util.Scanner;

/**
 *
 * @author wmartinl01
 */
public class T03Ejercicio23 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        int num_inicio, num_fin;
        do {
            System.out.print("Por favor, introduzca un numero: ");
            num_fin = entrada.nextInt();
            if (num_fin<=1) {
                System.out.println("ERROR: El número introducido debe ser mayor a 1.");
            }
        } while (num_fin<=1);
        
        for (num_inicio = 1; num_inicio <= num_fin; num_inicio++) {
            System.out.print(num_inicio+"-");
        }
 
    }

}
