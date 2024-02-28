/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio07t8;

import java.util.Scanner;

/**
 *
 * @author admin
 */
public class Ejercicio07T8 {

    /* Pide y retorna una cadena de caracteres */
    public static String pedirCadena(){
        Scanner teclado = new Scanner(System.in);
        String cadena;
        System.out.print("Introduzca cadena de caracteres: ");
        cadena = teclado.nextLine();
        return cadena;
    }
    
    /* Muestra, caracter por caracter, el contenido de la cadena */
    public static void mostrarChatAChar(String cadena){
        for(int i = 0;i < cadena.length();i++){
            System.out.println("Caracter: "+cadena.charAt(i));
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String cadena = pedirCadena();
        mostrarChatAChar(cadena);
    }
}
