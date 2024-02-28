package t07ejercicio09;

import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Test {

    public static int mostrarMenu() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("\n====================MENU====================");
        System.out.println("1. Rellenar las notas de los alumnos.\n"
                + "2. Mostrar las notas de los alumnos.\n"
                + "3. ¿Cual es el mejor alumno de la clase?.\n"
                + "4. ¿Cual es el alumno con mas suspensos de la clase?.\n"
                + "5. ¿Cual es la asignatura mas dificil?.\n"
                + "6. Salir del programa.");
        System.out.println("============================================");
        System.out.print("Por favor, introduzca una opcion: ");
        System.out.println("");//Salto de linea
        return entrada.nextInt();
    }

    public static void rellenarNotas(Alumno[] aula, String[] nombreAsignaturas) {
        for (int i = 0; i < aula.length; i++) { //Recorre los Alumnos
            Asignatura[] notas = new Asignatura[nombreAsignaturas.length]; //Creo Asignatura[] para cada alumno
            for (int j = 0; j < notas.length; j++) {//Relleno el Asignatura[]
                notas[j] = new Asignatura();
                notas[j].setNombreAsignatura(nombreAsignaturas[j]);
                notas[j].setNota(Math.round(Math.random() * 10));
            }
            aula[i].setNotas(notas); //Se lo asigno 
        }
    }

    public static void mostrarNotas(Alumno[] aula) {
        for (int i = 0; i < aula.length; i++) { //Recorro alumnos
            System.out.println("====== " + aula[i].getNombreAlumno() + " ======");
            aula[i].mostrarNotas();
        }
    }

    public static void mejorAlumno(Alumno[] aula) {
        //Creo vector para almacenar las medias
        float[] notasMedia = new float[aula.length];

        //Aisgno las notas medias 
        for (int j = 0; j < notasMedia.length; j++) {
            notasMedia[j] = aula[j].calcularMedia();
        }

        //Comparo cual es el alumno con la nota media mas alta
        int posicionMejorAlumno = 0;
        for (int i = 0; i < notasMedia.length; i++) {
            if (notasMedia[posicionMejorAlumno] < notasMedia[i]) {
                posicionMejorAlumno = i;
            }
        }

        //Muestro resultado
        System.out.println("El mejor alumno es " + aula[posicionMejorAlumno].getNombreAlumno() + " con una nota media de " + notasMedia[posicionMejorAlumno]);
    }

    public static void peorAlumno(Alumno[] aula) {
        //Creo vector para almacenar los suspensos de cada alumno
        int[] numeroSuspensos = new int[aula.length];

        //Relleno numeroSuspensos []
        for (int j = 0; j < numeroSuspensos.length; j++) {
            numeroSuspensos[j] = aula[j].numeroSuspensos();
        }

        //Comparo cual es el alumno con la nota media mas alta
        int posicionPeorAlumno = 0;
        for (int i = 0; i < numeroSuspensos.length; i++) {
            if (numeroSuspensos[posicionPeorAlumno] < numeroSuspensos[i]) {
                posicionPeorAlumno = i;
            }
        }

        //Muestro resultado
        System.out.println("El peor alumno es " + aula[posicionPeorAlumno].getNombreAlumno() + " con " + numeroSuspensos[posicionPeorAlumno] + " suspensos.");
    }

    public static void asignaturaMasDificil(Alumno[] aula, String[] nombreAsignaturas) {
        //Creo vector para almacenar los suspensos de cada asignatura
        int[] numeroSuspensos = new int[aula[0].getNotas().length];

        //Compruebo las notas y relleno el numeroSuspensos []
        for (int i = 0; i < aula.length; i++) {
            Asignatura[] notas = aula[i].getNotas(); //Creo Asignatura[] para asignarle las notas de cada uno
            for (int j = 0; j < numeroSuspensos.length; j++) { //Recorro las notas de cada asignatura para ver los suspensos
                if (notas[j].getNota() < 5) {
                    numeroSuspensos[j]++;
                }
            }
        }

        //Comparo cual es la asignatura con mas suspensos
        int asignaturaDificil = 0;
        for (int i = 0; i < numeroSuspensos.length; i++) {
            if (numeroSuspensos[asignaturaDificil] < numeroSuspensos[i]) {
                asignaturaDificil = i;
            }
        }

        //Muestro resultado
        System.out.println("La asignatura mas dificil es " + nombreAsignaturas[asignaturaDificil] + " con " + numeroSuspensos[asignaturaDificil] + " suspensos.");
    }

    public static void main(String[] args) {
        //Creamos los Alumnos y el array aula
        Alumno alumno1 = new Alumno("Pepe");
        Alumno alumno2 = new Alumno("Juan");
        Alumno alumno3 = new Alumno("Marta");
        Alumno[] aula = {alumno1, alumno2, alumno3};

        //Creamos el array asignaturas
        String[] nombreAsignaturas = {"Lengua", "Mates", "Historia", "Fisica"};

        //Variables
        int opcion;

        //MENU
        do {
            opcion = mostrarMenu();
            switch (opcion) {
                case 1:
                    System.out.println("==========RELLENA LAS NOTAS==========");
                    rellenarNotas(aula, nombreAsignaturas);
                    System.out.println("NOTAS RELLENADAS AUTOMATICAMENTE");
                    break;
                case 2:
                    System.out.println("==========MOSTRANDO LAS NOTAS==========");
                    mostrarNotas(aula);
                    break;
                case 3:
                    System.out.println("============MEJOR ALUMNO============");
                    mejorAlumno(aula);
                    break;
                case 4:
                    System.out.println("=============ALUMNO CON MAS SUSPENSOS==============");
                    peorAlumno(aula);
                    break;
                case 5:
                    System.out.println("=============ASIGNATURA MAS DIFICIL==============");
                    asignaturaMasDificil(aula, nombreAsignaturas);
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
