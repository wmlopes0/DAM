/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t04ejercicio08;

import java.util.Scanner;

/**
 *
 * @author wmartinl01
 */
public class T04Ejercicio08 {

    public static void descomponer(int cantidad) {
        
        int aux, billete50, billete20, billete10, billete5, moneda2, moneda1;

        billete50 = cantidad / 50;
        aux = cantidad % 50;

        billete20 = aux / 20;
        aux = aux % 20;

        billete10 = aux / 10;
        aux = aux % 10;

        billete5 = aux / 5;
        aux = aux % 5;

        moneda2 = aux / 2;

        moneda1 = aux % 2;

        System.out.println(cantidad + " Euros se descomponen en: ");

        if (billete50 > 0) {
            System.out.println("Billetes de 50: " + billete50);
        }
        if (billete20 > 0) {
            System.out.println("Billetes de 20: " + billete20);
        }
        if (billete10 > 0) {
            System.out.println("Billetes de 10: " + billete10);
        }
        if (billete5 > 0) {
            System.out.println("Billetes de 5: " + billete5);
        }
        if (moneda2 > 0) {
            System.out.println("Monedas de 2 euros: " + moneda2);
        }
        if (moneda1 > 0) {
            System.out.println("Monedas de 1 euro: " + moneda1);
        }
    }

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        int cantidad;

        System.out.print("Por favor, indique una cantidad de dinero: ");
        cantidad = entrada.nextInt();
        
        descomponer(cantidad);
    }

}
