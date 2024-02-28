/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio19t8;

import java.util.StringTokenizer;

/**
 *
 * @author admin
 */
public class Ejercicio19T8 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        StringTokenizer cadena = new StringTokenizer("Quique#Pineda#27061984#646464646", "#");
        while(cadena.hasMoreTokens()){
            System.out.println(cadena.nextToken());
        }
    }
    
}
