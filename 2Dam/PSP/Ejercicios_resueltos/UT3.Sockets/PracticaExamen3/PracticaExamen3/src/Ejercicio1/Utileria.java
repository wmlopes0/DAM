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

    //Mensaje que implementa la lógica necesaria y te devuelve un String con el mensaje que le tienes que mandar al cliente
    public static  String comprobarItv(Coche coche) {
        String respuesta = "#" + coche.getMarca() + " " + coche.getModelo() + " con matrícula " + coche.getMatricula() + ".";
        LocalDate hoy = LocalDate.now();
        int anosCoche = hoy.getYear() - coche.getAnoMatriculacion();
        switch (anosCoche) {
            case 1:
            case 2:
            case 3:
            case 4:
                respuesta += "EXENTO DE ITV.";
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                respuesta += "DEBE PASAR LA ITV CADA 2 AÑOS.";
                break;
            default:
                respuesta += "DEBE PASAR LA ITV CADA AÑO.";
        }
        return respuesta;
    }
}
