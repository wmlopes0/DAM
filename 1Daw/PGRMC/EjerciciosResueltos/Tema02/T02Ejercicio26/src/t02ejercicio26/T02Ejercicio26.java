/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t02ejercicio26;
import java.util.Scanner;
/**
 *
 * @author wmartinl01
 */
public class T02Ejercicio26 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        
        String numero;
        String num1;
        String num2;
        String num3;
        String num4;
        
        System.out.println("Por favor, indique un número de 4 cifras:");
        numero=entrada.nextLine();
        
        num1 = numero.substring(0, 1);
        num2 = numero.substring(1, 2);
        num3 = numero.substring(2, 3);
        num4 = numero.substring(3, 4);
        
        
        System.out.println("La primera cifra es: "+num1);
        System.out.println("La segunda cifra es: "+num2);
        System.out.println("La tercera cifra es: "+num3);
        System.out.println("La cuarta cifra es: "+num4);
        
        
        
    }
    
}
