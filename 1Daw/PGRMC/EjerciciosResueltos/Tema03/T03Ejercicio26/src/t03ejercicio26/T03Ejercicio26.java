/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t03ejercicio26;

/**
 *
 * @author Walter
 */
public class T03Ejercicio26 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int resultado=0;
        for (int i = 111; i <= 222; i++) {
            if (i % 2 != 0) {
                resultado+=i;
            }
        }
        System.out.println("La suma total de numeros pares comprendidos entre 17 y 139 es: "+resultado);
    }
    
}
