/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t04ejercicio04;

import java.util.Scanner;

/**
 *
 * @author wmartinl01
 */
public class T04Ejercicio04 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Scanner entrada = new Scanner(System.in);
        
        int num1, num2, num3;
        
        System.out.println("Por favor, introduzca el primer numero: ");
        num1 = entrada.nextInt();
        
        System.out.println("Ahora, introduzca el segundo numero: ");
        num2 = entrada.nextInt();
        
        System.out.println("Por último, introduzca el tercer numero: ");
        num3 = entrada.nextInt();
        
        ClaseDistinta.mostrarMenor(num1, num2, num3);
    }
    
}
