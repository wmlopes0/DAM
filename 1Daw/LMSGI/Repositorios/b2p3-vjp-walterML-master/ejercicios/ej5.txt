/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t02ejercicio22;
import java.util.Scanner;
/**
 *
 * @author Walter Martín Lopes
 */
public class T02Ejercicio22 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner (System.in);
        System.out.println("Por favor, introduzca la medida de un lado: ");
        double lado = entrada.nextDouble();
        
        double area = (lado*lado)/2;
        double perimetro= lado*3;
        
        System.out.println("El área de un triángulo de lado: "+lado+" es: "+area);
        System.out.println("El perímetro de un triángulo de lado: "+lado+" es: "+perimetro);
    }
    
}
