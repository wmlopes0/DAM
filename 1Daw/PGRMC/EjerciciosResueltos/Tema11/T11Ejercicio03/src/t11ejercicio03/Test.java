/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t11ejercicio03;

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
                + "1. Aparcar autobus.\n"
                + "2. Mostrar plazas libres.\n"
                + "3. Buscar autobus por matricula.\n"
                + "4. Buscar conductor.\n"
                + "5. ¿Que autobus tiene mas conductores asignados?.\n"
                + "6. Salir del programa.\n"
                + "--------------------------------------------------\n"
                + "Eliga una opcion: ");
        return entrada.nextInt();
    }

    public static int pedirPlaza() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduzca un numero de plaza(1-6): ");
        return entrada.nextInt() - 1;
    }

    //Metodo que devuelve TRUE si la plaza esta vacia
    public static boolean comprobarPlaza(int plaza, Autobus[] aparcamiento) {
        return aparcamiento[plaza] == null;
    }

    public static void aparcarAutobus(Autobus[] aparcamiento) {
        int plaza = pedirPlaza();

        //Compruebo que el usuario introduzca el numero de una plaza vacia
        while (!comprobarPlaza(plaza, aparcamiento)) {
            plaza = pedirPlaza();
        }

        //Creo Autobus e introduzco datos
        aparcamiento[plaza] = new Autobus();
        aparcamiento[plaza].rellenarAutobus();
    }

    public static void mostrarPlazasLibres(Autobus[] aparcamiento) {
        System.out.println("Actualmente hay las siguientes plazas libres: ");
        for (int i = 0; i < aparcamiento.length; i++) {
            if (aparcamiento[i] == null) {
                System.out.println("- Plaza " + (i + 1));
            }
        }
    }

    public static void buscarAutobus(Autobus[] aparcamiento) {
        String matricula = pedirMatricula();
        int i = 0;
        boolean encontrado = false;
        while (!encontrado && i < aparcamiento.length) {
            Autobus autobus = aparcamiento[i];
            if (autobus != null && autobus.getMatricula().equalsIgnoreCase(matricula)) {
                autobus.mostrarAutobus();
                encontrado = true;
            }
            i++;
        }
        if (!encontrado) {
            System.out.println("No se encontró ningún autobús con la matrícula " + matricula);
        }
    }

    public static String pedirMatricula() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce una matricula: ");
        return entrada.nextLine();
    }

    public static void buscarConductor(Autobus[] aparcamiento) {
        String dni = pedirDni();
        for (Autobus autobus : aparcamiento) {
            if (autobus != null) {
                autobus.buscarConductor(dni);
            }
        }
    }

    public static String pedirDni() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce un DNI: ");
        return entrada.nextLine();
    }

    public static int autobusMasConductores(Autobus[] aparcamiento) {
        int conductores = 0;
        int pos = -1;
        for (int i = 0; i < aparcamiento.length; i++) {
            if (aparcamiento[i] != null) {
                if (aparcamiento[i].numeroConductores() > conductores) {
                    conductores = aparcamiento[i].numeroConductores();
                    pos = i;
                }
            }
        }
        return pos;
    }

    public static void main(String[] args) {
        //Creo el vector de 6 aparcamientos
        Autobus[] aparcamiento = new Autobus[6];

        int opcion;
        do {
            opcion = mostrarMenu();
            switch (opcion) {
                case 1:
                    System.out.println("=======APARCAR AUTOBUS=======");
                    aparcarAutobus(aparcamiento);
                    break;
                case 2:
                    System.out.println("=======MOSTRANDO PLAZAS LIBRES=======");
                    mostrarPlazasLibres(aparcamiento);
                    break;
                case 3:
                    System.out.println("=======BUSCAR AUTOBUS POR MATRICULA=======");
                    buscarAutobus(aparcamiento);
                    break;
                case 4:
                    System.out.println("=======BUSCAR CONDUCTOR=======");
                    buscarConductor(aparcamiento);
                    break;
                case 5:
                    System.out.println("=======AUTOBUS CON MAS CONDUCTORES=======");
                    System.out.println("El autobus con el mayor numero de conductores ocupa la posicion " + autobusMasConductores(aparcamiento) + ".");
                    break;
                case 6:
                    System.out.println("¡HASTA PRONTO!");
                    break;
                default:
                    System.out.println("ERROR. Introduzca una opcion valida.");
            }
        } while (opcion != 6);

    }
}
