/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio04t8;

import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class Ejercicio04T8 {

    public static int pedirLongitud(){
        Scanner teclado = new Scanner(System.in);
        int longitud;
        System.out.print("¿Cuántas letras desea introducir? ");
        longitud = teclado.nextInt();
        return longitud;
    }

    //Pedimos al usuario que introduzca una cadena y la almacenamos en un vector de char
    public static void pedirCadena(char[] cadena){
        int caracter, escrito = 0;
        System.out.print("Introduzca la palabra a invertir: ");
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
    
    //Invierte la cadena que recibe por parámetros
    public static void invertirCadena(char[] cadena){
        char aux;
        int posFinal;
        for(int i = 0; i < (cadena.length/2);i++){
            aux = cadena[i];
            posFinal = (cadena.length - 1) - i;
            cadena[i] = cadena[posFinal];
            cadena[posFinal] = aux;
        }
    }
    
    //Muestra la cadena recibida por parámetros
    public static void mostrarCadena(char[] cadena){
        System.out.print("Palabra invertida: ");
        for(int i = 0; i < cadena.length;i++){
            System.out.print(cadena[i]);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        char[] cadena;
        int i, longitud, caracter, dniInt, escrito = 0;
        longitud = pedirLongitud();
        cadena = new char[longitud];
        pedirCadena(cadena);
        invertirCadena(cadena);
        mostrarCadena(cadena);
    }
    
}
