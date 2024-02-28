package Ejercicio1;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Walter
 */
public class Test {

    private static final File FICHERO = new File("carrun.obj");
    private static Set<Coche> catalogoCoches = new HashSet<>();

    //GENERAL
    public static int mostrarMenu() {
        Scanner entrada = new Scanner(System.in);
        int opcion;
        System.out.println("*****************************************************");
        System.out.println("******************* C A R - R U N *******************");
        System.out.println("*****************************************************");
        System.out.println("1.- Insertar coche.");
        System.out.println("2.- Poner a cero los km por año de matriculacion.");
        System.out.println("3.- Buscar por matricula.");
        System.out.println("4.- Salir del programa.");
        System.out.println("-----------------------------------------------------");
        System.out.println("Por favor, introduzca una opcion: ");
        try {
            opcion = entrada.nextInt();
        } catch (InputMismatchException e) {
            opcion = -1;
        }
        return opcion;
    }

    public static int pedirEntero(String texto) {
        Scanner entrada = new Scanner(System.in);
        int entero = 0;
        boolean valido;

        do {
            valido = true;
            System.out.println("Introduce " + texto + ": ");
            try {
                entero = entrada.nextInt();
            } catch (InputMismatchException e) {
                valido = false;
                entrada.next();
            }
        } while (!valido);

        return entero;
    }

    public static String pedirCadena(String texto) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce " + texto + ": ");
        return entrada.nextLine();
    }

    //Este metodo lee el fichero y almacena la info en el set
    public static void leerFichero() throws FileNotFoundException, IOException, ClassNotFoundException {
        Coche coche;
        //ABRO FLUJOS
        FileInputStream fis = new FileInputStream(FICHERO);
        ObjectInputStream ois = new ObjectInputStream(fis);

        //LEO Y ALMACENO
        try {
            while (true) {
                coche = (Coche) ois.readObject();
                catalogoCoches.add(coche);
            }
        } catch (EOFException e) {
        }

        //CIERRO FLUJOS
        ois.close();
        fis.close();
    }

    //Este metodo almacena la informacion del set en el fichero
    public static void escribirFichero() throws FileNotFoundException, IOException {
        //ABRO FLUJOS
        FileOutputStream fos = new FileOutputStream(FICHERO);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        //ESCRIBO
        for (Coche coche : catalogoCoches) {
            oos.writeObject(coche);
        }

        //CIERRO FLUJOS
        oos.close();
        fos.close();
    }

    //OPCION 1
    public static void insertarCoche() {
        Coche coche = new Coche();
        coche.rellenarInfo();
        catalogoCoches.add(coche);
    }

    //OPCION 2
    public static void resetearKms() {
        int ano = pedirEntero("el año de matriculacion");
        boolean encontrado = false;

        //RECORRO SET COMPARANDO AÑO DE MATRICULACION
        for (Coche coche : catalogoCoches) {
            if (coche.getFechaMatriculacion().getYear() == ano) {
                coche.setKms(0);
                encontrado = true;
            }
        }

        //MUESTRO MENSAJE CORRESPONDIENTE
        if (encontrado) {
            System.out.println("Kms reseteados correctamente para los coches matriculados en " + ano);
        } else {
            System.out.println("No se encontro ningun coche matriculado en " + ano);
        }
    }

    //OPCION 3
    public static void buscarPorMatricula() {
        String matricula = pedirCadena("la matricula");
        boolean encontrado = false;

        //RECORRO SET COMPARANDO LA MATRICULA
        for (Coche coche : catalogoCoches) {
            if (coche.getMatricula().equalsIgnoreCase(matricula)) {
                coche.mostrarInfo();
                encontrado = true;
            }
        }

        //MUESTRO MENSAJE CORRESPONDIENTE
        if (!encontrado) {
            System.out.println("No se encontro ningun coche con matricula: " + matricula);
        }
    }

    public static void main(String[] args) {
        int opcion;

        //CARGO LOS DATOS DEL FICHERO EN EL SET
        try {
            leerFichero();
        } catch (FileNotFoundException ex) {
        } catch (ClassNotFoundException ex) {
            System.out.println("ERROR.CLASE NO ENCONTRADA.");
        } catch (IOException ex) {
            System.out.println("ERROR DE ENTRADA/SALIDA.");
        }
        //-------------------------------------

        do {
            opcion = mostrarMenu();
            switch (opcion) {
                case 1:
                    System.out.println("******INSERTAR COCHE******");
                    insertarCoche();
                    break;
                case 2:
                    System.out.println("******PONER A 0 LOS KM******");
                    resetearKms();
                    break;
                case 3:
                    System.out.println("******BUSCAR POR MATRICULA******");
                    buscarPorMatricula();
                    break;
                case 4:
                    System.out.println("¡HASTA PRONTO!");
                    break;
                default:
                    System.out.println("ERROR. INTRODUZCA UNA OPCIÓN VALIDA.");
            }

        } while (opcion != 4);

        //VUELCO LOS DATOS DEL SET EN EL FICHERO
        try {
            escribirFichero();
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR.FICHERO NO ENCONTRADO.");
        } catch (IOException ex) {
            System.out.println("ERROR DE ENTRADA/SALIDA.");
        }
        //-------------------------------------
    }
}
