/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t02ejercicio21;

import java.util.Scanner;
/**
 *
 * @author Walter Martín Lopes
 */
public class T02Ejercicio21 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner entrada = new Scanner (System.in);
        
        System.out.println("Por favor, introduzca un número de segundos: ");
        int tiempo = entrada.nextInt();
        int aux,dias,horas,min,seg;
        dias = tiempo/86400;
        aux = tiempo%86400;
        horas = aux/3600;
        aux = aux%3600;
        min = aux/60;
        seg = aux%60;
        
       
        
        System.out.println(tiempo+" segundos hacen un total de: "+dias+" días, "+horas+" horas, "+min+" minutos y "+seg+ " segundos.");
    }
    
}
