/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t02ejercicio20;
import java.util.Scanner;
/**
 *
 * @author Walter Martín Lopes
 */
public class T02Ejercicio20 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner (System.in);
        
        double numero1;
        double numero2;
        int suma;
        
        System.out.print("\t Escriba el primer entero: ");
        numero1 = entrada.nextDouble();
        System.out.print("\t Escriba el segundo entero: ");
        numero2 = entrada.nextDouble();
        
        suma = (int)numero1 + (int)numero2;
        System.out.println("La suma es: "+suma);
    }
    
}
