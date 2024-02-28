/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t04ejercicio01;

import java.util.Scanner;

/**
 *
 * @author wmartinl01
 */
public class T04Ejercicio01 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int num;
        
        System.out.print("Por favor, introduzca un número: ");
        num = entrada.nextInt();
        
        PositivoNegativo(num);
    }
    
    public static void PositivoNegativo(int num){
        if (num<0) {
            System.out.println("El número "+num+" es negativo.");
        } else {
            System.out.println("El número "+num+" es positivo.");
        }
    }
    
}
