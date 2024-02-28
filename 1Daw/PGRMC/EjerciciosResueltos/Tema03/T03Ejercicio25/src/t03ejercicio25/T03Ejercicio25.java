/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t03ejercicio25;

/**
 *
 * @author wmartinl01
 */
public class T03Ejercicio25 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int resultado=0;
        for (int i = 17; i <= 139; i++) {
            if (i % 2 == 0) {
                resultado+=i;
            }
        }
        System.out.println("La suma total de numeros pares comprendidos entre 17 y 139 es: "+resultado);
    }

}
