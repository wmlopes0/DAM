/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t03ejercicio02;
import java.util.Scanner;
/**
 *
 * @author Walter Martín Lopes
 */
public class T03Ejercicio02 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int resultado;
        System.out.print("Por favor, introduzca un número: ");
        int numero1 = entrada.nextInt();
        System.out.print("Ahora, introduzca un segundo número: ");
        int numero2 = entrada.nextInt();
        
        if(numero1>=10){
            resultado = numero1*numero2;
            System.out.println("La operación que se realizó es producto, y el resultado es: "+resultado);
        } else {
            resultado = numero1+numero2;
            System.out.println("La operación que se realizó es suma, y el resultado es: "+resultado);
        }
    }
    
}
