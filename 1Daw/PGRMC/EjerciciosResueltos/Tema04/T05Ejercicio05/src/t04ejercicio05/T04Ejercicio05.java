/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t04ejercicio05;

import java.util.Scanner;
import nuevoPaquete.NuevaClase;

/**
 *
 * @author wmartinl01
 */
public class T04Ejercicio05 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        
        int num;
        
        System.out.println("Por favor, introduzca un numero por pantalla: ");
        num = entrada.nextInt();
        
        NuevaClase.parImpar(num);
    }
    
}
