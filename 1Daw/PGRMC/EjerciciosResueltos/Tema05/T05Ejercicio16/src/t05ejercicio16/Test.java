/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t05ejercicio16;

import java.util.Scanner;

/**
 *
 * @author wmartinl01
 */
public class Test {

    public static String pedirNombre() {
        Scanner nombre = new Scanner(System.in);

        System.out.println("Por favor, introduzca un nombre: ");
        return nombre.nextLine();
    }

    public static int pedirNota() {
        Scanner entrada = new Scanner(System.in);

        System.out.println("Por favor, introduzca una nota: ");
        int nota = entrada.nextInt();

        //Controlo que la nota tenga valores correctos
        if (nota < 0 || nota > 10) {
            System.out.println("ERROR: La nota introducida debe estar comprendida entre 0 y 10");
            nota = 0;
        }
        return nota;
    }

    public static void mostrarCalificacion(Alumno alumno) {
        int nota = alumno.getNota();
        String calificacion;

        //Asigno el valor correspondiente a la variable calificacion
        if (nota >= 0 && nota <= 4) {
            calificacion = "Suspenso";
        } else {
            if (nota >= 5 && nota <= 6) {
                calificacion = "Bien";
            } else {
                if (nota >= 7 && nota <= 8) {
                    calificacion = "Notable";
                } else {
                    calificacion = "Sobresaliente";
                }
            }
        }

        //Muestro el resultado
        System.out.println("La calificacion de " + alumno.getNombre() + " es " + calificacion+".");

    }

    public static void main(String[] args) {

        //Creo 3 Alumnos
        Alumno alumno1 = new Alumno(pedirNombre(), pedirNota());
        Alumno alumno2 = new Alumno(pedirNombre(), pedirNota());
        Alumno alumno3 = new Alumno(pedirNombre(), pedirNota());

        //Muestro la calificación
        mostrarCalificacion(alumno1);
        mostrarCalificacion(alumno2);
        mostrarCalificacion(alumno3);

    }
}
