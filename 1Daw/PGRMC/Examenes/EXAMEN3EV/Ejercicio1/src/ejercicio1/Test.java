package ejercicio1;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author final23
 */
public class Test {

    private static final File FICHERO = new File("alarmas.obj");

    //GENERAL
    public static int mostrarMenu() {
        Scanner entrada = new Scanner(System.in);
        int opcion;
        System.out.println("*************************************");
        System.out.println("**** T E L E A S I S T E N C I A ****");
        System.out.println("*************************************");
        System.out.println("1.- Registrar alarma.");
        System.out.println("2.- Mostrar alarmas registradas.");
        System.out.println("3.- Buscar por teleoperador.");
        System.out.println("4.- Salir.");
        System.out.println("-------------------------------------");
        System.out.println("Por favor, introduzca una opcion: ");
        try {
            opcion = entrada.nextInt();
        } catch (InputMismatchException e) {
            opcion = -1;
        }
        return opcion;
    }

    //Estos métodos son privados porque no tiene sentido llamarlos si no es desde registrarAlarma()
    private static void escribirEnFicheroNuevo(Alarma alarma) throws FileNotFoundException, IOException, ClassNotFoundException {
        //ABRO FLUJOS
        FileOutputStream fos = new FileOutputStream(FICHERO, true);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        //ESCRIBO
        oos.writeObject(alarma);

        //CIERRO FLUJOS
        oos.close();
        fos.close();
    }

    private static void escribirEnFicheroExistente(Alarma alarma) throws FileNotFoundException, IOException, ClassNotFoundException {
        //ABRO FLUJOS
        FileOutputStream fos = new FileOutputStream(FICHERO, true);
        MiObjectOutputStream oos = new MiObjectOutputStream(fos);

        //ESCRIBO
        oos.writeObject(alarma);

        //CIERRO FLUJOS
        oos.close();
        fos.close();
    }

    public static String pedirCadena(String mensaje) {
        Scanner entrada = new Scanner(System.in);
        System.out.println(mensaje);
        return entrada.nextLine();
    }

    //OPCION 1
    public static void registrarAlarma() {
        //Creo un objeto Alarma
        Alarma alarma = new Alarma();
        //Relleno info
        alarma.rellenarInfo();
        //Escribo la alarma en el fichero, si el fichero ya existe uso MiObjectOutputStream
        if (!FICHERO.exists()) {
            try {
                escribirEnFicheroNuevo(alarma);
            } catch (ClassNotFoundException ex) {
                System.out.println("ERROR: CLASE NO ENCONTRADA.");
            } catch (FileNotFoundException ex) {
                //No quiero que haga nada
            } catch (IOException ex) {
                System.out.println("ERROR DE ENTRADA/SALIDA.");
            }
        } else {
            try {
                escribirEnFicheroExistente(alarma);
            } catch (ClassNotFoundException ex) {
                System.out.println("ERROR: CLASE NO ENCONTRADA.");
            } catch (FileNotFoundException ex) {
                //No quiero que haga nada
            } catch (IOException ex) {
                System.out.println("ERROR DE ENTRADA/SALIDA.");
            }
        }
    }

    //OPCION 2
    public static void mostrarAlarmas() throws FileNotFoundException, IOException, ClassNotFoundException {
        Alarma alarma;

        //ABRO FLUJOS
        FileInputStream fis = new FileInputStream(FICHERO);
        ObjectInputStream ois = new ObjectInputStream(fis);

        //LEO
        try {
            while (true) {
                alarma = (Alarma) ois.readObject();
                alarma.mostrarInfo();
            }
        } catch (EOFException e) {
            //FIN
        }

        //CIERRO FLUJOS
        ois.close();
        fis.close();
    }

    //OPCION 3
    public static void buscarPorTeleoperador(String nombreTeleoperador) throws FileNotFoundException, IOException, ClassNotFoundException {
        Alarma alarma;
        int numeroAlarmas = 0;
        //ABRO FLUJOS
        FileInputStream fis = new FileInputStream(FICHERO);
        ObjectInputStream ois = new ObjectInputStream(fis);

        //LEO
        try {
            while (true) {
                alarma = (Alarma) ois.readObject();
                if (alarma.getNombreTeleoperador().equalsIgnoreCase(nombreTeleoperador)) {
                    numeroAlarmas++;
                }
            }
        } catch (EOFException e) {
            //FIN
        }

        //CIERRO FLUJOS
        ois.close();
        fis.close();

        //MUESTRO MENSAJE
        System.out.println("RESULTADO: El numero de alarmas de " + nombreTeleoperador + " es: " + numeroAlarmas);
    }

    public static void main(String[] args) {
        int opcion;
        String nombreTeleoperador;
        do {
            opcion = mostrarMenu();
            switch (opcion) {
                case 1:
                    System.out.println("******* REGISTRAR ALARMA *******");
                    registrarAlarma();
                    break;
                case 2:
                    System.out.println("******* MOSTRAR ALARMAS *******");
                    try {
                        mostrarAlarmas();
                    } catch (ClassNotFoundException ex) {
                        System.out.println("ERROR: CLASE NO ENCONTRADA.");
                    } catch (FileNotFoundException ex) {
                        System.out.println("ERROR: FICHERO NO ENCONTRADO, DEBES INTRODUCIR ALGUNA ALARMA ANTES.");
                    } catch (IOException ex) {
                        System.out.println("ERROR DE ENTRADA/SALIDA.");
                    }
                    break;
                case 3:
                    System.out.println("******* BUSCAR POR TELEOPERADOR *******");
                    nombreTeleoperador = pedirCadena("Introduce el nombre del teleoperador: ");
                    try {
                        buscarPorTeleoperador(nombreTeleoperador);
                    } catch (ClassNotFoundException ex) {
                        System.out.println("ERROR: CLASE NO ENCONTRADA.");
                    } catch (FileNotFoundException ex) {
                        System.out.println("ERROR: FICHERO NO ENCONTRADO, DEBES INTRODUCIR ALGUNA ALARMA ANTES.");
                    } catch (IOException ex) {
                        System.out.println("ERROR DE ENTRADA/SALIDA.");
                    }
                    break;
                case 4:
                    System.out.println("¡HASTA PRONTO!");
                    break;
                default:
                    System.out.println("ERROR: Introduzca una opcion valida.");
            }
        } while (opcion != 4);

    }
}
