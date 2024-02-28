package t07ejercicio01;

import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Test {

    public static float pedirNota(String nombre) {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Introduzca la nota de " + nombre + ": ");
        return entrada.nextFloat();
    }

    public static void rellenarNotas(Asignatura[] asignaturas, String[] nombres) {
        for (int i = 0; i < asignaturas.length; i++) {
            asignaturas[i] = new Asignatura(nombres[i], pedirNota(nombres[i]));
        }
    }

    public static float notaMedia(Asignatura[] asignaturas) {
        float notaMedia = 0;
        for (Asignatura asignatura : asignaturas) {
            notaMedia += asignatura.getNota();
        }
        return notaMedia / asignaturas.length;
    }

    public static void main(String[] args) {
        //Declaro arrays
        Asignatura[] asignaturas = new Asignatura[6];
        String[] nombres = {"Programacion", "Lenguajes de Marcas", "Bases de Datos", "Entornos de Desarrollo", "Sistemas Informaticos", "Formacion y Orientacion Laboral"};

        rellenarNotas(asignaturas, nombres);
        System.out.println("Su nota media del curso es de: " + notaMedia(asignaturas));

    }
}
