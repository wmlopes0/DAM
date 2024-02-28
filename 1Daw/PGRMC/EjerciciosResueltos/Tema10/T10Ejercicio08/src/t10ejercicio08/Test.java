package t10ejercicio08;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Test {

    public static void rellenarLista(ArrayList<Alumno> alumnos) {
        do {
            alumnos.add(new Alumno(pedirNombre(), pedirCurso()));
        } while (continuar());
    }

    public static String pedirNombre() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce un nombre:");
        return entrada.nextLine();
    }

    public static int pedirCurso() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce un curso:");
        return entrada.nextInt();
    }

    public static boolean continuar() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("¿Desea introducir mas alumnos?");
        return entrada.nextLine().equalsIgnoreCase("si");
    }

    public static void mostrarLista(ArrayList<Alumno> alumnos) {
        for (Alumno alumno : alumnos) {
            System.out.println(alumno.toString());
        }
    }

    public static void main(String[] args) {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        rellenarLista(alumnos);
        mostrarLista(alumnos);
    }
}
