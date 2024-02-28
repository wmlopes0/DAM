/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t03ejercicio04;
import java.util.Scanner;
/**
 *
 * @author Walter Martín Lopes
 */
public class T03Ejercicio04 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         Scanner entrada = new Scanner(System.in);
        
        System.out.println("Por favor, introduzca el primer numero: ");
        int num1 = entrada.nextInt();
        System.out.println("Ahora, introduzca el segundo numero: ");
        int num2 = entrada.nextInt();
        System.out.println("Por ultimo, introduzca el tercer numero: ");
        int num3 = entrada.nextInt();
        
        if(num1<num2 && num1<num3){
            System.out.println("El numero menor es "+num1+".");
        } else{
            if(num2<num1 && num2<num3){
                System.out.println("El numero menor es "+num2+".");
            } else{
                System.out.println("El numero menor es "+num3+".");
            }
        }
    }
    
}
