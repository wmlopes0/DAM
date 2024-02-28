package reservavuelos;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Utilidades {

    public static int pedirEntero(String texto) {
        Scanner s = new Scanner(System.in);
        int entero;
        System.out.println(texto);
        try {
            entero = s.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("ERROR.NO PUEDES INTRODUCIR UNA LETRA.");
            entero = pedirEntero(texto);
        }
        return entero;
    }

    public static String pedirCadena(String texto) {
        Scanner s = new Scanner(System.in);
        System.out.println(texto);
        return s.nextLine();
    }

    public static LocalDate pedirFecha() {
        Scanner s = new Scanner(System.in);
        LocalDate fecha;
        String linea;
        //Pido por pantalla la fecha en formato 2023-10-01
        System.out.println("Introduce la fecha en formato '2023-10-01': ");
        linea = s.nextLine();
        //Controlo que sea válida, sino utilizo recursividad
        try {
            fecha = LocalDate.parse(linea); //Este método se encarga de transformar un String con este formato '2023-10-01' en un LocalDate
        } catch (DateTimeException e) {
            System.out.println("ERROR.INTRODUZCA UNA FECHA VÁLIDA.");
            fecha = pedirFecha();
        }
        return fecha;
    }

    public static boolean seguir(String texto) {
        Scanner s = new Scanner(System.in);
        System.out.println(texto);
        return s.nextLine().equalsIgnoreCase("si");
    }

    public static boolean comprobarRequisitosPasajero(Pasajero pasajero) {
        return pasajero.getEdad() >= Main.EDAD_MINIMA;
    }
}
