package Ejercicio1;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Utileria {

    public static String pedirCadena(String texto) {
        Scanner entrada = new Scanner(System.in);
        System.out.println(texto);
        return entrada.nextLine();
    }

    public static int pedirEntero(String texto) {
        Scanner entrada = new Scanner(System.in);
        int numero;
        try {
            System.out.println(texto);
            numero = entrada.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("ERROR: No puedes introducir una letra.");
            numero = pedirEntero(texto);
        }
        return numero;
    }

    public static LocalDate pedirFecha() {
        Scanner entrada = new Scanner(System.in);
        String[] input;
        LocalDate fecha;
        try {
            System.out.println("Introduzca la fecha en formato 'dd-mm-yyy':");
            input = entrada.nextLine().split("-");
            fecha = LocalDate.of(Integer.parseInt(input[2]), Integer.parseInt(input[1]), Integer.parseInt(input[0]));

        } catch (ArrayIndexOutOfBoundsException | DateTimeException e) {
            System.out.println("ERROR: formato inválido.");
            fecha = pedirFecha();
        }
        return fecha;
    }

    public static boolean seguir(String texto) {
        Scanner entrada = new Scanner(System.in);
        System.out.println(texto);
        return entrada.nextLine().equalsIgnoreCase("si");
    }
}
