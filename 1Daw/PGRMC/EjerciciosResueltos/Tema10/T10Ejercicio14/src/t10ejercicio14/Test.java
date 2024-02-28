package t10ejercicio14;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Test {

    public static int mostrarMenu() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("\n"
                + "*************************************************\n"
                + "******************** M E N U ********************\n"
                + "*************************************************\n"
                + "1. Introducir un nuevo alumno junto a sus notas.\n"
                + "2. Mostrar los alumnos introducidos hasta el momento.\n"
                + "3. Mostrar mejor alumno de la clase (nota media mas alta).\n"
                + "4. Mostrar asignatura mas dificil (mayor numero de suspensos).\n"
                + "5. Salir.\n"
                + "--------------------------------------------------\n"
                + "Eliga una opcion: ");
        return entrada.nextInt();
    }

    public static void introducirAlumno(ArrayList<Alumno> alumnos) {
        Alumno alumno = new Alumno();
        alumno.rellenarAlumno();
        alumnos.add(alumno);
    }

    public static void mostrarAlumnos(ArrayList<Alumno> alumnos) {
        for (Alumno alumno : alumnos) {
            alumno.mostrarAlumno();
        }
    }

    public static void mostrarAlumnosIt(ArrayList<Alumno> alumnos) {
        Iterator<Alumno> it = alumnos.iterator();
        while (it.hasNext()) {
            it.next().mostrarAlumno();
        }
    }

    public static void mejorAlumno(ArrayList<Alumno> alumnos) {
        Alumno mejorAlumno = alumnos.get(0);
        for (Alumno alumno : alumnos) {
            if (mejorAlumno.notaMedia() < alumno.notaMedia()) {
                mejorAlumno = alumno;
            }
        }
        mejorAlumno.mostrarAlumno();
    }

    public static void asignaturaMasDificil(ArrayList<Alumno> alumnos) {
        int longitud = alumnos.get(0).getAsignaturas().length;
        int suspensos;
        int suspensosMaximos = 0;
        String asignaturaDificil = "";
        for (int i = 0; i < longitud; i++) {
            suspensos = 0;
            for (Alumno a : alumnos) {
                Asignatura asignatura = a.getAsignaturas()[i];
                if (asignatura.getNota() < 5) {
                    suspensos++;
                }
            }
            if (suspensos > suspensosMaximos) {
                suspensosMaximos = suspensos;
                asignaturaDificil = alumnos.get(0).getAsignaturas()[i].getNombre();
            }
        }
        System.out.println("La asignatura mas dificil es " + asignaturaDificil + " con " + suspensosMaximos + " suspensos.");
    }

    public static void main(String[] args) {
        ArrayList<Alumno> alumnos = new ArrayList<>();

        int opcion;
        do {
            opcion = mostrarMenu();
            switch (opcion) {
                case 1:
                    System.out.println("# AÑADIR NUEVO ALUMNO");
                    introducirAlumno(alumnos);
                    break;
                case 2:
                    if (!alumnos.isEmpty()) {
                        System.out.println("# MOSTRAR ALUMNOS");
                        mostrarAlumnos(alumnos);
                        //mostrarAlumnosIt(alumnos);
                    } else {
                        System.out.println("Error.Primero debes introducir algun alumno.");
                    }
                    break;
                case 3:
                    if (!alumnos.isEmpty()) {
                        System.out.println("# MEJOR ALUMNO");
                        mejorAlumno(alumnos);
                    } else {
                        System.out.println("Error.Primero debes introducir algun alumno.");
                    }
                    break;
                case 4:
                    if (!alumnos.isEmpty()) {
                        System.out.println("# ASIGNATURA MAS DIFICIL");
                        asignaturaMasDificil(alumnos);
                    } else {
                        System.out.println("Error.Primero debes introducir algun alumno.");
                    }
                    break;
                case 5:
                    System.out.println("¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Error.Introduzca un opcion valida.");

            }

        } while (opcion != 5);
    }
}
