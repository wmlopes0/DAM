package t12ejercicio15;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Test {

    public static int mostrarMenu(Scanner entrada) {
        System.out.println("========MENU========");
        System.out.println("1. Añadir contacto a 'agenda.bin'.");
        System.out.println("2. Mostrar por pantalla el contenido de 'agenda.bin'.");
        System.out.println("3. Salir del programa.");
        System.out.println("----------------------");
        System.out.println("Por favor introduce una opcion: ");
        return entrada.nextInt();
    }

    public static void crearContacto(File fichero) {
        //CREAMOS CONTACTO
        Contacto contacto = new Contacto();
        contacto.rellenarContacto();
        //ESCRIBIMOS EL CONTACTO
        try {
            escribirContacto(contacto, fichero);
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR.FICHERO NO ENCONTRADO.");
        } catch (IOException ex) {
            System.out.println("ERROR DE ESCRITURA.");
        }
    }

    public static void escribirContacto(Contacto contacto, File fichero) throws FileNotFoundException, IOException {
        //ABRIMOS FLUJOS NECESARIOS
        FileOutputStream fos = new FileOutputStream(fichero, true);
        DataOutputStream dos = new DataOutputStream(fos);

        //ESCRIBIMOS
        dos.writeUTF(contacto.getNombre());
        dos.writeInt(contacto.getEdad());
        dos.writeInt(contacto.getTelefono());

        //CERRAMOS FLUJOS
        dos.close();
        fos.close();
    }

    public static void leerFichero(File fichero) throws FileNotFoundException, IOException {
        //ABRIMOS FLUJOS NECESARIOS
        FileInputStream fis = new FileInputStream(fichero);
        DataInputStream dis = new DataInputStream(fis);

        //LEEMOS
        while (dis.available() > 0) {
            System.out.println(dis.readUTF() + ", " + dis.readInt() + ", " + dis.readInt());
        }

        //CERRAMOS FLUJOS
        dis.close();
        fis.close();

    }

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        File fichero = new File("agenda.bin");
        int opcion;

        do {
            opcion = mostrarMenu(entrada);
            switch (opcion) {
                case 1:
                    crearContacto(fichero);
                    break;
                case 2: 
                    System.out.println("LEYENDO FICHERO...");
                    try {
                    leerFichero(fichero);
                } catch (FileNotFoundException ex) {
                    System.out.println("FICHERO NO ENCONTRADO");
                } catch (IOException ex) {
                    System.out.println("ERROR DE LECTURA.");
                }
                break;
                case 3:
                    System.out.println("¡HASTA PRONTO!");
                    break;
                default:
                    System.out.println("ERROR. POR FAVOR INTRODUZCA UNA OPCION VALIDA.");
            }

        } while (opcion != 3);
    }
}
