/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t11ejercicio07;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

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
                + "1. Añadir campaña. \n"
                + "2. Añadir donacion. \n"
                + "3. Mostrar campañas junto con donaciones.\n"
                + "4. Mostrar campaña por nombre. \n"
                + "5. Mostrar total dinero recaudado. \n"
                + "6. Mostrar mayor donacion. \n"
                + "7. Salir.");
        System.out.println("----------------------------------------------");
        System.out.println("Introduce una opcion: ");
        return entrada.nextInt();
    }

    //OPCIÓN 1=======================================================================
    public static void anadirCampania(Map<String, Campania> campanias) {
        String nombreCampania = pedirCadena(" de la campaña");
        Campania campania = new Campania(nombreCampania);
        campanias.put(nombreCampania, campania);
    }

    //OPCIÓN 2=======================================================================
    //Comprueba si existe la campaña introducida, si existe introduce donaciones sino manda mensaje error.
    public static void comprobarAnadirCampania(Map<String, Campania> campanias) {
        String nombreCampania;
        nombreCampania = pedirCadena(" de la campaña");
        Campania campania;
        if (existeCampania(nombreCampania, campanias)) {
            campania = campanias.get(nombreCampania);
            anadirDonacion(campania);
        } else {
            System.out.println("Error. La campaña introducida no existe.");
        }
    }

    //Retorna True si existe la campaña
    public static boolean existeCampania(String nombre, Map<String, Campania> campanias) {
        boolean existe = false;
        if (campanias.containsKey(nombre)) {
            existe = true;
        }
        return existe;
    }

    //Añade donaciones hasta que diga que no
    public static void anadirDonacion(Campania campania) {
        String nombreDonante, nombreCampania;
        float cantidad;

        nombreCampania = campania.getNombre();

        do {
            System.out.println("-----DONACION-----");
            nombreDonante = pedirCadena(" del donante");
            cantidad = pedirCantidad();
            campania.introducirDonacion(nombreDonante, cantidad);
            System.out.println("------------------");
        } while (seguir(nombreCampania));
    }

    //OPCIÓN 3=======================================================================
    public static void mostrarCampanias(Map<String, Campania> campanias) {
        Campania campania;
        for (String clave : campanias.keySet()) {
            campania = campanias.get(clave);
            campania.mostrarCampania();
        }
    }

    //OPCIÓN 4=======================================================================
    public static void comprobarMostrarCampania(Map<String, Campania> campanias) {
        String nombreCampania;
        Campania campania;
        nombreCampania = pedirCadena(" de la campaña");
        if (campanias.containsKey(nombreCampania)) {
            campania = campanias.get(nombreCampania);
            campania.mostrarCampania();
        }
    }

    //OPCIÓN 5=======================================================================
    public static float totalRecaudacionCampanias(Map<String, Campania> campanias) {
        float recaudacion = 0;
        Campania campania;
        for (String clave : campanias.keySet()) {
            campania = campanias.get(clave);
            recaudacion += campania.totalRecaudacion();
        }
        return recaudacion;
    }

    //OPCIÓN 6=======================================================================
    public static void mostrarMayorDonacion(Map<String, Campania> campanias) {
        Campania campania;
        Set<Donacion> donaciones;
        String nombreCampania = "";
        String nombreDonante = "";
        float mayorDonacion = 0;

        for (String clave : campanias.keySet()) {
            campania = campanias.get(clave);
            donaciones = campania.getDonaciones();
            for (Donacion donacion : donaciones) {
                if (donacion.getCantidad() > mayorDonacion) {
                    nombreCampania = campania.getNombre();
                    nombreDonante = donacion.getNombre();
                    mayorDonacion = donacion.getCantidad();
                }
            }
        }
        System.out.println("La mayor donacion la hizo " + nombreDonante + " con un importe de " + mayorDonacion + " en la campaña con nombre " + nombreCampania);

    }

    //Métodos pedir---------------------------------------------
    public static boolean seguir(String nombre) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("¿Desea seguir añadiendo donaciones en la campaña " + nombre + "? ");
        return entrada.nextLine().equalsIgnoreCase("si");
    }

    public static String pedirCadena(String texto) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce el nombre" + texto + ": ");
        return entrada.nextLine();
    }

    public static float pedirCantidad() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce la cantidad donada: ");
        return entrada.nextFloat();
    }
    //----------------------------------------------------------

    public static void main(String[] args) {
        //Creo un Set de campañas
        Map<String, Campania> campanias = new HashMap<>();

        int opcion;
        do {
            opcion = mostrarMenu();
            switch (opcion) {
                case 1:
                    System.out.println("=======AÑADIR CAMPAÑA=======");
                    anadirCampania(campanias);
                    System.out.println("¡Campaña añadida con exito!");
                    break;
                case 2:
                    System.out.println("=======AÑADIR DONACION=======");
                    comprobarAnadirCampania(campanias);
                    break;
                case 3:
                    System.out.println("=======MOSTRAR CAMPAÑAS Y DONACIONES=======");
                    mostrarCampanias(campanias);
                    break;
                case 4:
                    System.out.println("=======MOSTRAR CAMPAÑA POR NOMBRE=======");
                    comprobarMostrarCampania(campanias);
                    break;
                case 5:
                    System.out.println("=======MOSTRAR TOTAL DINERO RECAUDADO=======");
                    System.out.println("El total de dinero recaudado entre todas las campañas asciende a " + totalRecaudacionCampanias(campanias) + " euros.");
                    break;
                case 6:
                    System.out.println("=======MOSTRAR MAYOR DONACION=======");
                    mostrarMayorDonacion(campanias);
                    break;
                case 7:
                    System.out.println("¡HASTA PRONTO!");
                    break;
                default:
                    System.out.println("ERROR. Introduzca una opcion valida.");
            }
        } while (opcion != 7);

    }
}
