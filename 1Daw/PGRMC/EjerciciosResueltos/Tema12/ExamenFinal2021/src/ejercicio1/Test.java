package ejercicio1;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Test {

    //FICHERO
     private static final File FICHERO = new File("logistica.obj");

    //GENERAL
    public static int mostrarMenu() {
        Scanner entrada = new Scanner(System.in);
        int opcion;
        System.out.println("=====================================================");
        System.out.println("===========Plataforma Logistica de Badajoz===========");
        System.out.println("=====================================================");
        System.out.println("1.- Insertar nave.");
        System.out.println("2.- Mostrar naves.");
        System.out.println("3.- Insertar empresa.");
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

    public static String pedirNombreNave() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduzca el nombre de la nave: ");
        return entrada.nextLine();
    }

    public static void cargarDatos(List<Nave> naves) throws FileNotFoundException, IOException, ClassNotFoundException {
        Nave naveAuxiliar;

        //Abro flujos
        FileInputStream fis = new FileInputStream(FICHERO);
        ObjectInputStream ois = new ObjectInputStream(fis);

        //LEO
        try {
            while (true) {
                naveAuxiliar = (Nave) ois.readObject();
                naves.add(naveAuxiliar);
            }
        } catch (EOFException e) {
        }
        //Cierro flujos
        ois.close();
        fis.close();
    }

    public static void volcarDatos(List<Nave> naves) throws FileNotFoundException, IOException {
        Nave naveAuxiliar;
        //Abro flujos
        FileOutputStream fos = new FileOutputStream(FICHERO);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        //Escribo
        for (int i = 0; i < naves.size(); i++) {
            naveAuxiliar = naves.get(i);
            oos.writeObject(naveAuxiliar);
        }
        //Cierro flujos
        oos.close();
        fos.close();
    }

    //OPCION1
    public static void insertarNave(List<Nave> naves) {
        //Creo objeto nave
        Nave nave = new Nave();
        //Relleno objeto nave
        nave.rellenarInfoNave();
        //La añado a la lista
        naves.add(nave);
    }

    //OPCION2
    public static void mostrarNaves(List<Nave> naves) {
        for (Nave nave : naves) {
            nave.mostrarInfoNave();
        }
    }

    //OPCION3
    public static Nave buscarNave(String nombre, List<Nave> naves) {
        Nave nave = null;
        boolean encontrado = false;
        int i = 0;
        while (i < naves.size() && encontrado == false) {
            if (naves.get(i).getNombre().equalsIgnoreCase(nombre)) {
                nave = naves.get(i);
                encontrado = true;
            } else {
                i++;
            }
        }
        return nave;
    }

    public static void insertarEmpresa(List<Nave> naves) {
        Nave nave;
        Empresa empresa;
        String nombreNave;
        nombreNave = pedirNombreNave();
        //Si la encuentra retorna el objeto nave, si no retorna null
        nave = buscarNave(nombreNave, naves);
        //Si existe relleno datos empresa sino mensaje error
        if (nave != null) {
            empresa = new Empresa();
            empresa.rellenarInfoEmpresa();
            nave.getEmpresas().add(empresa);
        } else {
            System.out.println("ERROR.LA NAVE NO EXISTE.");
        }
    }

    //MAIN
    public static void main(String[] args) {
        List<Nave> naves = new ArrayList<>();
        int opcion;

        //CARGAR DATOS EN NAVES-----------------------------------
        try {

            cargarDatos(naves);
        } catch (FileNotFoundException ex) {
        } catch (ClassNotFoundException ex) {
            System.out.println("ERROR.CLASE NO ENCONTRADA.");
        } catch (IOException ex) {
            System.out.println("ERROR ENTRADA/SALIDA.");
        }
        //-------------------------------------------------------

        do {
            opcion = mostrarMenu();
            switch (opcion) {
                case 1:
                    System.out.println("******INSERTAR NAVE******");
                    insertarNave(naves);
                    break;
                case 2:
                    System.out.println("******MOSTRAR NAVES******");
                    mostrarNaves(naves);
                    break;
                case 3:
                    System.out.println("******INSERTAR EMPRESA******");
                    insertarEmpresa(naves);
                    break;
                case 4:
                    System.out.println("¡HASTA PRONTO!");
                    break;
                default:
                    System.out.println("ERROR. INTRODUZCA UNA OPCIÓN VALIDA.");
            }

        } while (opcion != 4);

        //VOLCAR DATOS EN FICHERO-----------------------------------
        try {
            volcarDatos(naves);
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
            System.out.println("ERROR ENTRADA/SALIDA.");
        }
        //-------------------------------------------------------
        
    }
}
