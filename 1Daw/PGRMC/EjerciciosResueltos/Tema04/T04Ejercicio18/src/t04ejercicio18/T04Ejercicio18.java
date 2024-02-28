package t04ejercicio18;

import java.util.Scanner;

/**
 *
 * @author wmartinl01
 */
public class T04Ejercicio18 {

    public static String pedirNombre() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Por favor, introduzca su nombre: ");
        String nombre = entrada.nextLine();

        return nombre;
    }

    public static void mostrarNombre(String nombre) {
        for (int i = 1; i <= 5; i++) {
            System.out.println(i + "- " + nombre);
        }
    }

    public static void main(String[] args) {
        mostrarNombre(pedirNombre());
    }

}
