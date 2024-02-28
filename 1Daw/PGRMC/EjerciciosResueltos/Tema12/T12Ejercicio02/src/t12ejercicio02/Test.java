package t12ejercicio02;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Walter
 */
public class Test {

    public static int mostrarMenu() throws InputMismatchException {
        Scanner entrada = new Scanner(System.in);
        System.out.println("==========MENU==========");
        System.out.println("1.- Crear un directorio que se llame “ejemplo”.");
        System.out.println("2.- Dentro de él crear dos ficheros llamados “practica1.txt” y “practica2.txt”.");
        System.out.println("3.- Renombrar el fichero practica1.txt y llámar “renombrado.txt”.");
        System.out.println("4.- Elimina el fichero “practica2.txt”.");
        System.out.println("5.- Salir.");
        System.out.println("--------------------------");
        System.out.println("Por favor, introduzca una opcion: ");
        return entrada.nextInt();
    }

    public static void crearDirectorio() {
        File ejemplo;
        ejemplo = new File("ejemplo");

        if (ejemplo.mkdir()) {
            System.out.println("Directorio creado correctamente.");
        } else {
            System.out.println("No se pudo crear el directorio.");
        }
    }

    public static void crearFicheros() {
        File practica1 = new File("ejemplo", "practica1.txt");
        File practica2 = new File("ejemplo", "practica2.txt");

        try {
            practica1.createNewFile();
            practica2.createNewFile();
        } catch (IOException ex) {
            System.out.println("ERROR");
        }

    }

    public static void renombrarFichero() {
        File practica1 = new File("ejemplo", "practica1.txt");
        File renombrado = new File("ejemplo", "renombrado.txt");
        practica1.renameTo(renombrado);
    }

    public static void eliminarFichero() {
        File practica2 = new File("ejemplo", "practica2.txt");

        practica2.delete();
    }

    public static void main(String[] args) {
        int opcion;
        do {
            try {
                opcion = mostrarMenu();
            } catch (InputMismatchException e) {
                opcion = 6;
            }

            switch (opcion) {
                case 1:
                    crearDirectorio();
                    break;
                case 2:
                    crearFicheros();
                    break;
                case 3:
                    renombrarFichero();
                    break;
                case 4:
                    eliminarFichero();
                    break;
                case 5:
                    System.out.println("¡HASTA PRONTO!");
                    break;
                default:
                    System.out.println("Por favor, introduzca una opcion valida.");
            }
        } while (opcion != 5);
    }
}
