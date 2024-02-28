/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t02ejercicio29;
import java.util.Scanner;
/**
 *
 * @author Walter Martin Lopes
 */
public class T02Ejercicio29 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        
        System.out.print("Por favor, introduce la longitud del cateto 1 en cm: ");
        int cateto1 = entrada.nextInt();
        
        System.out.print("Introduce la longitud del cateto 2 en cm: ");
        int cateto2 = entrada.nextInt();
        
        double hipotenusa = Math.sqrt(Math.pow(cateto1,2)+Math.pow(cateto2,2));
        
        System.out.println("La hipotenusa de un triángulo rectangulo cuyo cateto 1 mide "+cateto1+" cm y el cateto 2 mide "+cateto2+" cm, es de "+(float)hipotenusa+" cm.");
        
    }
    
}
