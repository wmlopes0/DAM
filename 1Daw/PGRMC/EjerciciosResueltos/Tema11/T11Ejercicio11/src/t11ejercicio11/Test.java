/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t11ejercicio11;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Walter
 */
public class Test {

    public static int mostrarMenu() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("==========MENU==========");
        System.out.println("1.- Añadir alumno.");
        System.out.println("2.- Mostrar alumnos (ordenados de menor a mayot numero de expediente.");
        System.out.println("3.- Buscar por numero de expediente.");
        System.out.println("4.- Mostrar alumnos por nota.");
        System.out.println("5.- Salir.");
        System.out.println("--------------------------");
        System.out.println("Por favor, introduzca una opcion: ");
        return entrada.nextInt();
    }

    public static void anadirAlumno(Set<Alumno> alumnos) {
        Alumno alumno;
        do {
            alumno = new Alumno();
            alumno.rellenar();
            alumnos.add(alumno);
        } while (seguir());
    }

    public static boolean seguir() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("¿Desea seguir introduciendo alumnos?");
        return entrada.nextLine().equalsIgnoreCase("si");
    }

    public static void mostrarAlumnos(Set<Alumno> alumnos) {
        for (Alumno alumno : alumnos) {
            alumno.mostrar();
        }
    }

    public static void buscarExpediente(int expediente, Set<Alumno> alumnos) {
        Iterator<Alumno> it = alumnos.iterator();
        boolean enc = false;
        Alumno alumno;

        while (it.hasNext() && !enc) {
            alumno = it.next();
            if (alumno.getExpediente() == expediente) {
                alumno.mostrar();
                enc = true;
            }
        }
    }

    public static int pedirExpediente() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce un numero de expediente: ");
        return entrada.nextInt();
    }

    public static float pedirNota() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce una nota: ");
        return entrada.nextFloat();
    }

    public static void mostrarAlumnos(float nota, Set<Alumno> alumnos) {
        for (Alumno alumno : alumnos) {
            if (alumno.getNotaMedia() == nota) {
                alumno.mostrar();
            }
        }
    }

    public static void main(String[] args) {
        Set<Alumno> alumnos = new TreeSet<>();
        int opcion, expediente;
        float nota;

        do {
            opcion = mostrarMenu();
            switch (opcion) {
                case 1:
                    System.out.println("#AÑADE ALUMNOS");
                    anadirAlumno(alumnos);
                    break;
                case 2:
                    System.out.println("#MOSTRAR ALUMNOS ORDENADOS POR EXPEDIENTE");
                    mostrarAlumnos(alumnos);
                    break;
                case 3:
                    System.out.println("#BUSCAR ALUMNO POR EXPEDIENTE");
                    expediente = pedirExpediente();
                    buscarExpediente(expediente, alumnos);
                    break;
                case 4:
                    System.out.println("#MOSTRAR ALUMNO POR NOTA MEDIA");
                    nota = pedirNota();
                    mostrarAlumnos(nota, alumnos);
                    break;
                case 5:
                    System.out.println("¡HASTA PRONTO!");
                    break;
                default:
                    System.out.println("Error. Introduzca una opcion valida.");
            }
        } while (opcion != 5);
    }
}
