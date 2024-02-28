/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t03ejercicio05;
import java.util.Scanner;
/**
 *
 * @author Walter Martín Lopes
 */
public class T03Ejercicio05 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Scanner entrada = new Scanner(System.in);
        System.out.print("Por favor, introduzca un numero: ");
        int numero = entrada.nextInt();
        
        if (numero%2 == 0){
            System.out.println("El numero es par.");
        } else {
            System.out.println("El numero es impar.");
        }
    }
    
}
