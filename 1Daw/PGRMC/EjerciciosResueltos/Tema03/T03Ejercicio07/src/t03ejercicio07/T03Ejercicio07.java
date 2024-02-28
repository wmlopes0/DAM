/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t03ejercicio07;
import java.util.Scanner;
/**
 *
 * @author Walter Martín Lopes
 * 
 * El siguiente programa pide por pantalla un número del 1 al 7 referente a cada día de la semana y te devuelve si es día laborable o no.
 */
public class T03Ejercicio07 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Scanner entrada = new Scanner(System.in);
       
        boolean laborable = false;
        
        System.out.println("Por favor, introduzca un dia de la semana en formato numero comprendido entre el 1 y el 7: ");
        byte diasemana = entrada.nextByte();
        
        switch (diasemana){
            case 1:
                laborable = true;
                break;
            case 2:
                laborable = true;
                break;
            case 3:
                laborable = true;
                break;
            case 4:
                laborable = true;
                break;
            case 5:
                laborable = true;
                break;
            case 6:
                laborable = false;
                break;    
            case 7:
                laborable = false;
                break;   
   
        }
        
        if (diasemana>=1 && diasemana<=7){
            if (laborable == true){
            System.out.println("El dia "+diasemana+" es un dia laborable.");
        } else {
            System.out.println("El dia "+diasemana+" no es un dia laborable.");
        }
        } else {
            System.out.println("ERROR: El numero introducido no se encuentra comprendido entre 1 y 7.");
        }
        
        
        
    }
    
}
