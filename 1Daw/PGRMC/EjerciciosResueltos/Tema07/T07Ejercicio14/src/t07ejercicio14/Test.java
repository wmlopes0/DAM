package t07ejercicio14;

import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Test {

    public static int mostrarMenu() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("\n====================MENU====================");
        System.out.println("1. Rellenar un alumno.\n"
                + "2. Mostrar los alumnos.\n"
                + "3. Mostrar alumnos con nota media por encima de una nota dada.\n"
                + "4. Mostrar cuantos alumnos hay con nota media suspensa.\n"
                + "5. Buscar alumno por nombre.\n"
                + "6. Salir del programa.");
        System.out.println("============================================");
        System.out.print("Por favor, introduzca una opcion: ");
        System.out.println("");//Salto de linea
        return entrada.nextInt();
    }

    public static boolean posicionOcupada(Alumno[] alumnos, int posicion) {
        return alumnos[posicion] != null;
    }

    public static String pedirNombre() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduzca un nombre: ");
        return entrada.nextLine();
    }

    public static int pedirEdad() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduzca la edad: ");
        return entrada.nextInt();
    }

    public static float pedirNotaMedia() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduzca la nota media: ");
        return entrada.nextFloat();
    }

    public static void rellenarAlumno(Alumno[] alumnos) {
        Scanner entrada = new Scanner(System.in);
        int posicion;

        //Pedimos posicion y comprobamos si esta vacia
        do {
            System.out.println("Por favor, introduzca una posicion (1-5): ");
            posicion = entrada.nextInt() - 1;
        } while (posicionOcupada(alumnos, posicion));

        //Asignamos un alumno nuevo a esa posicion y lo rellenamos
        alumnos[posicion] = new Alumno();
        alumnos[posicion].setNombre(pedirNombre());
        alumnos[posicion].setEdad(pedirEdad());
        alumnos[posicion].setNotaMedia(pedirNotaMedia());

    }

    public static void mostrarAlumnos(Alumno[] alumnos) {
        for (int i = 0; i < alumnos.length; i++) {
            if (alumnos[i] != null) {
                System.out.println("-------------POSICION " + (i + 1) + "---------------");
                System.out.println("Nombre: " + alumnos[i].getNombre());
                System.out.println("Edad: " + alumnos[i].getEdad());
                System.out.println("Nota media: " + alumnos[i].getNotaMedia());
            }
        }
    }

    public static void buscadorNotaMedia(Alumno[] alumnos) {
        float notaMedia = pedirNotaMedia();

        for (int i = 0; i < alumnos.length; i++) {
            if (alumnos[i] != null) {
                if (notaMedia <= alumnos[i].getNotaMedia()) {
                    System.out.println("-------------POSICION " + (i + 1) + "---------------");
                    System.out.println("Nombre: " + alumnos[i].getNombre());
                    System.out.println("Edad: " + alumnos[i].getEdad());
                    System.out.println("Nota media: " + alumnos[i].getNotaMedia());
                }
            }

        }
    }

    public static void alumnosSuspensos(Alumno[] alumnos) {
        int alumnosSuspensos = 0;
        for (int i = 0; i < alumnos.length; i++) {
            if (alumnos[i] != null) {
                if (alumnos[i].getNotaMedia() < 5) {
                    alumnosSuspensos++;
                }
            }

        }
        System.out.println("Hay " + alumnosSuspensos + " alumnos suspensos.");
    }

    public static void buscadorPorNombre(Alumno[] alumnos) {
        String nombre = pedirNombre();
        int i = 0;

        while (i < alumnos.length && (alumnos[i] == null || !alumnos[i].getNombre().equalsIgnoreCase(nombre))) {
            i++;
        }

        if (i < alumnos.length) {
            System.out.println(nombre + " esta matriculado.");
        } else {
            System.out.println(nombre + " no esta matriculado.");
        }
    }

    public static void main(String[] args) {
        //Creamos arrays necesarios
        Alumno[] alumnos = new Alumno[5];

        //Variables
        int opcion;

        //MENU
        do {
            opcion = mostrarMenu();
            switch (opcion) {
                case 1:
                    System.out.println("==========RELLENA ALUMNOS==========");
                    rellenarAlumno(alumnos);
                    break;
                case 2:
                    System.out.println("==========MOSTRANDO LOS ALUMNOS==========");
                    mostrarAlumnos(alumnos);
                    break;
                case 3:
                    System.out.println("============BUSCADOR POR NOTA MEDIA============");
                    buscadorNotaMedia(alumnos);
                    break;
                case 4:
                    System.out.println("=============ALUMNOS SUSPENSOS==============");
                    alumnosSuspensos(alumnos);
                    break;
                case 5:
                    System.out.println("=============BUSCADOR POR NOMBRE==============");
                    buscadorPorNombre(alumnos);
                    break;
                case 6:
                    System.out.println("¡HASTA PRONTO!");
                    break;
                default:
                    System.out.println("ERROR: Por favor, introduzca una opcion valida.");
            }
        } while (opcion != 6);
    }
}
