/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t03ejercicio24;

import java.util.Scanner;

/**
 *
 * @author wmartinl01
 */
public class T03Ejercicio24 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        int num_inicio, num_fin, contador = 0;

        do {
            System.out.println("Por favor introduzca un numero: ");
            num_fin = entrada.nextInt();
            if (num_fin < 0) {
                System.out.println("ERROR: El numero introducido no puede ser menor a 0.");
            }
        } while (num_fin < 0);

        for (num_inicio = 1; num_inicio <= num_fin; num_inicio++) {
            if (num_inicio % 3 == 0) {
                System.out.println(num_inicio);
                contador++;
            }
        }
        System.out.println("El total de multiplos de 3 encontrados entre " + num_inicio + " y " + num_fin + " ha sido: " + contador);
    }

}
