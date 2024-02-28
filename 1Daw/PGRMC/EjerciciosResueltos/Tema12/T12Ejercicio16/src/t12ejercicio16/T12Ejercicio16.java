package t12ejercicio16;

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
public class T12Ejercicio16 {

    public static int mostrarMenu(Scanner entrada) {
        System.out.println("========MENU========");
        System.out.println("1. Volcado de un array con los 100 primeros números impares a un fichero binario.");
        System.out.println("2. Mostrar por pantalla el contenido del fichero.");
        System.out.println("3. Salir del programa.");
        System.out.println("----------------------");
        System.out.println("Por favor introduce una opcion: ");
        return entrada.nextInt();
    }

    public static String pedirCadena(String texto) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce " + texto + ":");
        return entrada.nextLine();
    }

    public static int[] rellenarVector() {
        int[] vector = new int[100];
        int numero = 0;
        for (int i = 0; i < vector.length; i++) {
            while (numero % 2 == 0) {
                numero++;
            }
            vector[i] = numero;
            numero++;
        }
        return vector;
    }

    public static void escribirFichero(int[] impares, File fichero) throws FileNotFoundException, IOException {
        //ABRIMOS FLUJOS NECESARIOS
        FileOutputStream fos = new FileOutputStream(fichero, true);
        DataOutputStream dos = new DataOutputStream(fos);

        //ESCRIBIMOS
        for (int i = 0; i < impares.length; i++) {
            dos.writeInt(impares[i]);
        }

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
            System.out.println(" [" + dis.readInt() + "] ");
        }

        //CERRAMOS FLUJOS
        dis.close();
        fis.close();

    }

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int[] impares;
        File fichero = null;
        String nombreFichero;
        int opcion;

        do {
            opcion = mostrarMenu(entrada);
            switch (opcion) {
                case 1:
                    nombreFichero = pedirCadena("el nombre del fichero") + ".bin";
                    fichero = new File(nombreFichero);
                    impares = rellenarVector();
                    try {
                        escribirFichero(impares, fichero);
                    } catch (FileNotFoundException ex) {
                        System.out.println("ERROR.FICHERO NO ENCONTRADO.");
                    } catch (IOException ex) {
                        System.out.println("ERROR DE ESCRITURA.");
                    }
                    break;
                case 2:
                    System.out.println("LEYENDO FICHERO...");
                    try {
                        leerFichero(fichero);
                    } catch (FileNotFoundException | NullPointerException ex) {
                        System.out.println("ERROR.FICHERO NO ENCONTRADO.");
                    } catch (IOException ex) {
                        System.out.println("ERROR DE ESCRITURA.");
                    }
                    break;

                case 3:
                    System.out.println("¡HASTA PRONTO!");
                    break;
                default:
                    System.out.println("ERROR. POR FAVOR INTRODUZCA UNA OPCION VALIDA.");
            }

        } while (opcion
                != 3);
    }

}
