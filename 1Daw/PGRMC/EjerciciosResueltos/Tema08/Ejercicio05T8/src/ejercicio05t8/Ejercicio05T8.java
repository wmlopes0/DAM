/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio05t8;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author admin
 */
public class Ejercicio05T8 {

    public static int pedirLongitud(){
        Scanner teclado = new Scanner(System.in);
        int longitud;
        System.out.print("Introduzca longitud cadena: ");
        longitud = teclado.nextInt();
        return longitud;
    }
    
    public static void mostrarCadena(char cadena[]){
        for(char c:cadena){
            System.out.print("["+c+"]");
        }
    }
    
    //Pedimos al usuario que introduzca el DNI y la almacenamos en un vector de char
    public static void pedirCadena(char[] cadena){
        int caracter, escrito = 0;
        System.out.print("Introduzca DNI: ");
        //Recogemos valor del DNI en variable dni
        try{
            //Mientras el caracter sea distinto de 'Enter' y no me haya pasado de la longitud del vector cadena
            while(((caracter = System.in.read())!='\n') && (escrito<cadena.length)){
                cadena[escrito] = (char)caracter; //Almacenamos el caracter en la celda del vector cadena
                escrito++;
            }
        } catch(Exception e){
            System.out.println("Longitud del texto escrito: "+e.getMessage());
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        char[] cadena = new char[pedirLongitud()];
        pedirCadena(cadena);
        System.out.println("\nDesordenado");
        mostrarCadena(cadena);
        Arrays.sort(cadena);
        System.out.println("\nOrdenado");
        mostrarCadena(cadena);
    }
    
}
