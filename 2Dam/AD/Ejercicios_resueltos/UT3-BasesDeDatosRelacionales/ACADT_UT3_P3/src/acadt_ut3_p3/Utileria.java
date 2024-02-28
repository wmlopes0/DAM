package acadt_ut3_p3;

import java.sql.Date;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Utileria {
    //PEDIR ENTERO
    public static int pedirEntero(String texto) {
        Scanner s = new Scanner(System.in);
        int entero;
        System.out.println(texto);
        try {
            entero = s.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("ERROR:No puedes introducir una letra.");
            entero = pedirEntero(texto);
        }
        return entero;
    }

    //PEDIR FLOAT
    public static float pedirFloat(String texto) {
        Scanner s = new Scanner(System.in);
        float entero;
        System.out.println(texto);
        try {
            entero = s.nextFloat();
        } catch (InputMismatchException e) {
            System.out.println("ERROR:No puedes introducir una letra.");
            entero = pedirEntero(texto);
        }
        return entero;
    }

    //PEDIR STRING
    public static String pedirString(String texto) {
        Scanner s = new Scanner(System.in);
        System.out.println(texto);
        return s.nextLine();
    }

    //PEDIR DATE
    public static Date pedirDate() {
        Scanner s = new Scanner(System.in);
        LocalDate fecha;
        String fechaString = pedirString("Introduce la fecha en formato '1998-08-27':");
        String[] fechaArray = fechaString.split("-");
        try {
            fecha = LocalDate.of(Integer.valueOf(fechaArray[0]), Integer.valueOf(fechaArray[1]), Integer.valueOf(fechaArray[2]));
        } catch (DateTimeException e) {
            System.out.println("ERROR:Por favor, introduzca una fecha válida.");
            return pedirDate();
        }
        return Date.valueOf(fecha);
    }
}
