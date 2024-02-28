package t04ejercicio24;

import java.util.Scanner;

/**
 *
 * @author wmartinl01
 */
public class T04Ejercicio24 {

    public static String pedirNombre() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Por favor, introduzca su nombre: ");
        String nombre = entrada.nextLine();

        return nombre;
    }

    public static int pedirEdad() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Por favor, introduzca su edad: ");
        int edad = entrada.nextInt();

        return edad;
    }

    public static void mayorDeEdad(String nombre, int edad) {
        if (edad >= 18) {
            System.out.println("El usuario llamado " + nombre + " es mayor de edad.");
        } else {
            System.out.println("El usuario llamado " + nombre + " es menor de edad.");
        }
    }

    public static void main(String[] args) {
        String nombre = pedirNombre();
        int edad = pedirEdad();

        mayorDeEdad(nombre, edad);
    }

}
