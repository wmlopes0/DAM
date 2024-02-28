package t12ejercicio07;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class T12Ejercicio07 {

    public static String pedirCadena(String texto) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduzca " + texto + ": ");
        return entrada.nextLine();
    }

    public static boolean seguir() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Otra linea?");
        return entrada.nextLine().equalsIgnoreCase("si");
    }

    public static void escribirFichero(File fichero) throws IOException {
        Scanner entrada = new Scanner(System.in);

        //Abrimos flujos
        FileWriter fw = new FileWriter(fichero);
        PrintWriter pw = new PrintWriter(fw);

        //Escribimos
        do {
            System.out.println("Escribe: ");
            pw.println(entrada.nextLine());
        } while (seguir());

        //Cerramos flujos
        pw.close();
        fw.close();
    }

    public static String intercambiarMayusculas(String linea) {
        String cadenaCambiada = "";
        int caracter;
        for (int i = 0; i < linea.length(); i++) {
            caracter = (int) linea.charAt(i);
            if (caracter == 32) {
                cadenaCambiada += (char) caracter;
            } else if (caracter >= 97) {
                cadenaCambiada += (char) (caracter - 32);
            } else {
                cadenaCambiada += (char) (caracter + 32);
            }
        }
        return cadenaCambiada;
    }

    public static void leerFichero(File fichero) throws FileNotFoundException, IOException {
        String linea;
        //Abrimos flujos
        FileReader fr = new FileReader(fichero);
        BufferedReader br = new BufferedReader(fr);

        //LEEMOS E INTERCAMBIAMOS
        linea = br.readLine();
        while (linea != null) {
            linea = intercambiarMayusculas(linea);
            System.out.println(linea);
            linea = br.readLine();
        }

        //Cerramos flujos
        br.close();
        fr.close();
    }

    public static void main(String[] args) {
        String nombreFichero, texto;
        File fichero;

        System.out.println("******************************************");
        System.out.println("***************BIENVENIDO A***************");
        System.out.println("**********INTERCAMBIA MAYUSCULAS**********");
        System.out.println("******************************************");
        nombreFichero = pedirCadena(" el nombre del fichero ");
        fichero = new File(nombreFichero);
        try {
            fichero.createNewFile();
        } catch (IOException ex) {
            System.out.println("ERROR DE ENTRADA/SALIDA.");
        }

        //ESCRIBIMOS EL FICHERO
        System.out.println("Procedemos a rellenar el fichero... ");
        try {
            escribirFichero(fichero);
        } catch (FileNotFoundException ex) {
            System.out.println("FICHERO NO ENCONTRADO");
        } catch (IOException ex) {
            System.out.println("ERROR DE ENTRADA/SALIDA.");
        }

        //LEEMOS E INTERCAMBIAMOS EL FICHERO
        try {
            leerFichero(fichero);
        } catch (FileNotFoundException ex) {
            System.out.println("FICHERO NO ENCONTRADO");
        } catch (IOException ex) {
            System.out.println("ERROR DE ENTRADA/SALIDA.");
        }

    }

}
