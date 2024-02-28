package ejercicio2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author final23
 */
public class Test {

    //GENERAL
    public static int mostrarMenu() {
        Scanner entrada = new Scanner(System.in);
        int opcion;
        System.out.println("*************************************");
        System.out.println("******* PODCAST DE JORDI WILD *******");
        System.out.println("*************************************");
        System.out.println("1.- Añadir podcast.");
        System.out.println("2.- Mostrar podcasts.");
        System.out.println("3.- Buscar podcast.");
        System.out.println("4.- Generar informe segun plataforma.");
        System.out.println("5.- Salir del programa.");
        System.out.println("-------------------------------------");
        System.out.println("Por favor, introduzca una opcion: ");
        try {
            opcion = entrada.nextInt();
        } catch (InputMismatchException e) {
            opcion = -1;
        }
        return opcion;
    }

    public static String pedirCadena(String mensaje) {
        Scanner entrada = new Scanner(System.in);
        System.out.println(mensaje);
        return entrada.nextLine();
    }

    public static File generarNombreFichero(String nombrePlataforma) {
        File fichero;
        String nombreFichero;
        //Creo el nombre del fichero
        nombreFichero = nombrePlataforma + ".txt";
        //Genero el objeto file correspondiente con el nombre
        fichero = new File(nombreFichero);
        //Retorno el objeto file
        return fichero;
    }

    //OPCION 1
    public static void anadirPodcast(Map<String, Podcast> mapaPodcast) {
        //Creo un objeto Podcast
        Podcast podcast = new Podcast();
        //Relleno su info
        podcast.rellenarInfo();
        //Añado el podcast al mapa
        mapaPodcast.put(podcast.getNombre(), podcast);
    }

    //OPCION 2
    public static void mostrarPodcast(Map<String, Podcast> mapaPodcast) {
        Podcast podcast;
        for (String nombre : mapaPodcast.keySet()) {
            podcast = mapaPodcast.get(nombre);
            podcast.mostrarInfo();
        }
    }

    //OPCION 3
    public static void buscarPodcast(Map<String, Podcast> mapaPodcast, String nombrePodcast) {
        if (mapaPodcast.containsKey(nombrePodcast)) {
            mapaPodcast.get(nombrePodcast).mostrarPlataformas();
        } else {
            System.out.println("ERROR: El podcast introducido no existe.");
        }
    }

    //OPCION 4
    public static void generarInformePlataforma(Map<String, Podcast> mapaPodcast, String nombrePlataforma) throws IOException {
        //Creo variables necesarias
        File fichero = generarNombreFichero(nombrePlataforma);
        Podcast podcast;
        String linea;
        int numReproducciones;

        //ABRO FLUJOS
        FileWriter fw = new FileWriter(fichero);
        PrintWriter pw = new PrintWriter(fw);

        /*ESCRIBO CUANDO SEA NECESARIO, me recorro la estructura con un for each y voy comprobando si ese podcast existe en
        esa plataforma, si es así escribo la info en el fichero como corresponde.*/
        for (String nombre : mapaPodcast.keySet()) {
            podcast = mapaPodcast.get(nombre);
            numReproducciones = podcast.existePlataforma(nombrePlataforma);
            //Si numReproducciones es -1 significa que el podcast no está en esa plataforma y pasa a la siguiente
            if (numReproducciones != -1) {
                linea = podcast.getNombre() + "#" + numReproducciones;
                pw.println(linea);
            }
        }

        //CIERRO FLUJOS
        pw.close();
        fw.close();

        //Mensaje ok
        System.out.println("INFORME GENERADO CORRECTAMENTE.");
    }

    public static void main(String[] args) {
        int opcion;
        String nombrePodcast, nombrePlataforma;

        //CREO EL HASHMAP
        Map<String, Podcast> mapaPodcast = new HashMap<>();

        do {
            opcion = mostrarMenu();
            switch (opcion) {
                case 1:
                    System.out.println("******* AÑADIR PODCAST *******");
                    anadirPodcast(mapaPodcast);
                    break;
                case 2:
                    System.out.println("******* MOSTRAR PODCAST *******");
                    if (!mapaPodcast.isEmpty()) {
                        mostrarPodcast(mapaPodcast);
                    } else {
                        System.out.println("ERROR: Debes añadir primero almenos un podcast.");
                    }
                    break;
                case 3:
                    System.out.println("******* BUSCAR PODCAST *******");
                    if (!mapaPodcast.isEmpty()) {
                        //Pido el nombre del podcast
                        nombrePodcast = pedirCadena("Introduce el nombre del podcast: ");
                        buscarPodcast(mapaPodcast, nombrePodcast);
                    } else {
                        System.out.println("ERROR: Debes añadir primero almenos un podcast.");
                    }
                    break;
                case 4:
                    System.out.println("******* GENERAR INFORME SEGUN PLATAFORMA *******");
                    if (!mapaPodcast.isEmpty()) {
                        //Pido el nombre de la plataforma
                        nombrePlataforma = pedirCadena("Introduce el nombre de la plataforma: ");
                        try {
                            generarInformePlataforma(mapaPodcast, nombrePlataforma);
                        } catch (IOException ex) {
                            System.out.println("ERROR DE ENTRADA/SALIDA");
                        }
                    } else {
                        System.out.println("ERROR: Debes añadir primero almenos un podcast.");
                    }
                    break;
                case 5:
                    System.out.println("¡HASTA PRONTO!");
                    break;
                default:
                    System.out.println("ERROR: Introduzca una opcion valida.");
            }
        } while (opcion != 5);
    }
}
