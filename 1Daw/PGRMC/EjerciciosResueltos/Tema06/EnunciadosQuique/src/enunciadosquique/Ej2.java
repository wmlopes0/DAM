/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enunciadosquique;

import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Ej2 {

    public static int pedirNumero() {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Introduzca un numero: ");
        return entrada.nextInt();
    }

    public static boolean comprobarNumero(int num, char[] abecedario) {
        return num < abecedario.length && num >= 0;
    }

    public static void main(String[] args) {
        char[] abecedario = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        
        String cadena="";
        int numUsuario = pedirNumero();
        
        while (numUsuario != -1) {
            if (comprobarNumero(numUsuario, abecedario)) {
                cadena += abecedario[numUsuario];
                System.out.print("//Se ha añadido la '"+abecedario[numUsuario]+"'.\n");
            } else{
                System.out.print("//Error, inserte otro numero.");
            }
            numUsuario = pedirNumero();
        }
        System.out.print("//Fin.");
        System.out.println("\n Cadena resultante: "+cadena);
    }

}
