/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t03ejercicio06;
import java.util.Scanner;
/**
 *
 * @author Walter Martín Lopes
 */
public class T03Ejercicio06 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Por favor, introduzca la nota del alumno: ");
        float nota = entrada.nextFloat();
        
        if (nota>=0 && nota<=10){
            if (nota>=0 && nota<=4){
                System.out.println("Suspenso.");
            } else {
                if (nota>=5 && nota<=6){
                    System.out.println("Bien.");
                } else{
                    if (nota>=7 && nota<=8) {
                        System.out.println("Notable.");
                    } else {
                        System.out.println("Sobresaliente.");
                    }
                }
            }
        } else {
            System.out.println("ERROR: El numero introducido no se encuentra comprendido entre 0 y 10.");
        }
    }
    
}
