/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t02ejercicio25;
import java.util.Scanner;
/**
 *
 * @author Walter Martin Lopes
 */
public class T02Ejercicio25 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        float num1,num2,num3,suma,producto;
        
        System.out.println("Por favor, introduzca el primer número: ");
        num1 = entrada.nextFloat();
        System.out.println("Por favor, introduzca el segundo número: ");
        num2 = entrada.nextFloat();
        System.out.println("Por favor, introduzca el tercer número: ");
        num3 = entrada.nextFloat();
        
        suma = num1+num2+num3;
        System.out.println("La suma de los números introducidos es: "+suma);
        producto=num1*num2*num3;
        System.out.println("El producto de los números introducidos es: "+producto);
    }
    
}
