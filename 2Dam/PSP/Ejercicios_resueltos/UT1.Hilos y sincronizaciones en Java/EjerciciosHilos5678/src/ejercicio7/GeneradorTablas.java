/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio7;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author wmartinl01
 */
public class GeneradorTablas {

    public static void main(String[] args) {
        PintaTabla pintaTabla;
        while (seguir()) {
            pintaTabla = new PintaTabla(pedirNumero());
            pintaTabla.start();
            System.out.println("¡FICHERO CREADO EXITOSAMENTE!");
        }
    }

    public static boolean seguir() {
        Scanner s = new Scanner(System.in);
        System.out.println("¿Desea generar una tabla de multiplicar?");
        return s.nextLine().equalsIgnoreCase("si");
    }

    public static int pedirNumero() {
        Scanner s = new Scanner(System.in);
        int numero;
        System.out.println("Introduce el numero:");
        try {
            numero = s.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("ERROR.INTRODUCE UNA LETRA");
            numero = pedirNumero();
        }
        return numero;
    }
}
