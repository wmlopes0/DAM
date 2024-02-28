/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t02ejercicio24;
import java.util.Scanner;
/**
 *
 * @author Walter Martin Lopes
 */
public class T02Ejercicio24 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        //Declaro las variables
        float pgrmc,lmsgi,bbdd,entdl,ssinf,fol,notaMedia;
        
        //Pido las notas de cada asignatura
        System.out.println("Por favor, introduzca la nota de Programación:");
        pgrmc = entrada.nextFloat();
        
        System.out.println("Introduzca la nota de Lenguajes de Marcas:");
        lmsgi = entrada.nextFloat();
        
        System.out.println("Introduzca la nota de Bases de Datos:");
        bbdd = entrada.nextFloat();
        
        System.out.println("Introduzca la nota de Entornos de Desarrollo:");
        entdl = entrada.nextFloat();
        
        System.out.println("Introduzca la nota de Sistemas Informáticos:");
        ssinf = entrada.nextFloat();
        
        System.out.println("Por último, introduzca la nota de Formación y Orientación Laboral:");
        fol = entrada.nextFloat();
        
        //Calculo la nota media
        notaMedia = (pgrmc+lmsgi+bbdd+entdl+ssinf+fol)/6;
        
        //Muestro la nota media
        
        System.out.println("Su nota media del curso es de: "+notaMedia);
        
    }
    
}
