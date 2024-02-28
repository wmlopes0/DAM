/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t03ejercicio14;

/**
 *
 * @author wmartinl01
 */
public class T03Ejercicio14 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int num = 0;
        int contador = 0;
        
        do {
            if (num % 2 == 0) {
                System.out.print(num + "-");
                contador++;
            }
            num++;
        } while (contador <= 100);

    }

}
