/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio8;

/**
 *
 * @author wmartinl01
 */
public class Main {

    private static int[][] matriz1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    private static int[][] matriz2 = {{4, 2, 1}, {9, 8, 1}, {1, 1, 0}};
    private static int[][] matrizResultado = new int[3][3];

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Hilo hilo = new Hilo(matriz1[i][j], matriz2[i][j]);
                hilo.start();
                hilo.join();
                matrizResultado[i][j] = hilo.getSumaTotal();
            }
        }
        

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(matrizResultado[i][j] + "\t");
            }
            System.out.println("");
        }
    }
}
