/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio11t8;

import java.util.Scanner;

/**
 *
 * @author admin
 */
public class Ejercicio11T8 {

    public static String pedirFrase(){
        Scanner teclado = new Scanner(System.in);
        String frase;
        System.out.print("Itroduzca frase: ");
        frase = teclado.nextLine();
        return frase;
    }
    
    public static void mostrarInvertida(String frase){
        String[] vFrases = frase.split(" ");
        for(int i = vFrases.length-1;i >= 0;i--){
            System.out.println(vFrases[i]);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String frase;
        frase = pedirFrase();
        mostrarInvertida(frase);
    }
    
}
