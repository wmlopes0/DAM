/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t02ejercicio27;
import java.util.Scanner;
/**
 *
 * @author Walter Martin Lopes
 */
public class T02Ejercicio27 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        
        int numero;
        double doble;
        double cubo;
        
        System.out.print("Por favor, introduzca un número: ");
        numero = entrada.nextInt();
        
        doble = Math.pow(numero, 2);
        System.out.println("El doble de "+numero+" es: "+doble);
        
        cubo = Math.pow(numero, 3);
        System.out.println("El cubo de "+numero+" es: "+cubo);
    }
    
}
