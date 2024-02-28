/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio03t8;

import java.util.Scanner;

/**
 *
 * @author admin
 */
public class Ejercicio03T8 {
    
    public static int pedirLongitud(){
        Scanner teclado = new Scanner(System.in);
        int longitud;
        System.out.print("¿Cuántas letras desea introducir? ");
        longitud = teclado.nextInt();
        return longitud;
    }

    public static void mostrarCadena(char letras[]){
        for(int i = 0;i < letras.length;i++){
            System.out.print(letras[i]);
        }
        System.out.println("");
    }
    
    public static void cifrar(char letras[]){
        int aux;
        for(int i = 0;i < letras.length;i++){
            if  (letras[i] != ' '){
                aux = (int)letras[i];
                aux = aux + 3;
                if  (aux > 122){  //Si nos pasamos de la Z
                    aux = aux - 26;  //Volvemos al principio (la a es la 97)
                }
                letras[i] = (char)aux;                
            }
        }
    }
    
    //Pedimos al usuario que introduzca una cadena y la almacenamos en un vector de char
    public static void pedirCadena(char[] letras){
        int caracter, escrito = 0;
        System.out.print("Introduzca cadena. Pulse 'Enter' para finalizar: ");
        //Recogemos valor del DNI en variable dni
        try{
            //Mientras el caracter sea distinto de 'Enter' y no me haya pasado de la longitud del vector cadena
            while(((caracter = System.in.read())!='\n') && (escrito<letras.length)){
                letras[escrito] = (char)caracter; //Almacenamos el caracter en la celda del vector cadena
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
        Scanner teclado = new Scanner(System.in);
        char[] letras;
        int i, longitud, caracter, escrito = 0;
        longitud = pedirLongitud();
        letras = new char[longitud];
        pedirCadena(letras);
        System.out.println("Original: ");
        mostrarCadena(letras);
        cifrar(letras);
        System.out.println("Cifrada: ");
        mostrarCadena(letras);
    }
    
}
