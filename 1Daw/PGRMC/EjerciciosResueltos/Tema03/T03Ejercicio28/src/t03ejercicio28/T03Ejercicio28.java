/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t03ejercicio28;

/**
 *
 * @author Walter
 */
public class T03Ejercicio28 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int aleatorio = (int)Math.floor(Math.random() * 100);
        if (aleatorio%2==0) {
            System.out.println("El numero aleatorio es "+aleatorio+" y es par.");
        } else {
            System.out.println("El numero aleatorio es "+aleatorio+" y es impar.");
        }
    }

}
