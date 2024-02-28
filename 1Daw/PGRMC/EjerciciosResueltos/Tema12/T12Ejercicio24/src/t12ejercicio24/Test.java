package t12ejercicio24;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.management.Query;

/**
 *
 * @author Walter
 */
public class Test {

    final static File FICHERO = new File("emplados.obj");

    public static int mostrarMenu() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("========MENU========");
        System.out.println("1.- Añadir empleados.");
        System.out.println("2.- Mostrar empleados.");
        System.out.println("3.- Salir.");
        System.out.println("----------------------");
        System.out.println("Introduce una opcion: ");
        return entrada.nextInt();
    }

    public static boolean seguir() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("¿Desea seguir introduciendo empleados?");
        return entrada.nextLine().equalsIgnoreCase("si");
    }

    public static void anadirEmpleados(List<Empleado> empleados) {
        Empleado empleado;
        do {
            empleado = new Empleado();
            empleado.rellenar();
            empleados.add(empleado);
        } while (seguir());
    }

    public static void escribirFichero(List<Empleado> empleados) throws FileNotFoundException, IOException {
        if (!FICHERO.exists()) {
            //ABRO FLUJOS
            FileOutputStream fos = new FileOutputStream(FICHERO, true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            //ESCRIBO NOTAS
            for (int i = 0; i < empleados.size(); i++) {
                oos.writeObject(empleados.get(i));
            }

            //CIERRO FLUJOS
            oos.close();
            fos.close();
        } else {
            FileOutputStream fos = new FileOutputStream(FICHERO, true);
            MiObjectOutputStream moos = new MiObjectOutputStream(fos);

            //ESCRIBO NOTAS
            for (int i = 0; i < empleados.size(); i++) {
                moos.writeObject(empleados.get(i));
            }

            //CIERRO FLUJOS
            moos.close();
            fos.close();
        }
    }

    public static void mostrarEmpleados() throws IOException, ClassNotFoundException {
        Empleado auxiliar;

        //ABRO FLUJOS
        FileInputStream fis = new FileInputStream(FICHERO);
        ObjectInputStream ois = new ObjectInputStream(fis);

        try {
            //LEO NOTAS
            while (true) {
                auxiliar = (Empleado) ois.readObject();
                auxiliar.mostrar();
            }
        } catch (EOFException ex) {
        } finally {
            //CIERRO FLUJOS
            fis.close();
            ois.close();
        }
    }

    public static void main(String[] args) {
        List<Empleado> empleados = new ArrayList<>();

        int opcion;
        do {
            opcion = mostrarMenu();
            switch (opcion) {
                case 1:
                    anadirEmpleados(empleados);
                    try {
                        escribirFichero(empleados);
                    } catch (FileNotFoundException ex) {
                        System.out.println("FICHERO NO ENCONTRADO");
                    } catch (IOException ex) {
                        System.out.println("ERROR ENTRADA/SALIDA");
                    }
                    break;
                case 2: 
                    try {
                    mostrarEmpleados();
                } catch (FileNotFoundException ex) {
                    System.out.println("FICHERO NO ENCONTRADO");
                } catch (ClassNotFoundException ex) {
                    System.out.println("CLASE NO ENCONTRADA");
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                break;
                case 3:
                    System.out.println("HASTA PRONTO MANOLO");
                    break;
                default:
                    System.out.println("ERROR.INTRODUCE UNA OPCION VALIDA.");
            }

        } while (opcion != 3);
    }
}
